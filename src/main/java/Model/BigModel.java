package Model;

public class BigModel {

    public static void CreatePaymentAndUpdateVacation(Payments newPayment){

        SQLModel sql = SQLModel.GetInstance();
        //sql.insertRecordToTable(Tables.TBL_PAYMENTS.toString().toLowerCase(), newPayment);


        //Set request as approved:
        String[] requestFields = new String[TblFields.enumDict.get("requestTblFields").size()];
        String approvedReqId = newPayment.get_aprovedRequest();
        requestFields[0] = approvedReqId;

        Request approvedRequest = new Request(sql.selectFromTable(Tables.TBL_REQUESTS, requestFields));
        approvedRequest.setR_answer("Approved");
        sql.updateRecord(approvedRequest);

        //Set vacation as sold:
        String[] vacationFields = new String[TblFields.enumDict.get("vacationFields").size()];
        String vacationId = approvedRequest.getVacationID();
        vacationFields[0] = vacationId;

        Vacation vacation = new Vacation(sql.selectFromTable(Tables.TBL_VACATIONS, vacationFields));
        vacation.set_vacationStatus(Vacation.VacationStatus.SOLD);

        //Insert Payment to DB
        sql.insertRecordToTable(Tables.TBL_PAYMENTS.name().toLowerCase(), newPayment);
    }

    public static void CreateRequestAndUpdateVacation(Request newRequest) {
        //create request
        SQLModel sql = SQLModel.GetInstance();
        sql.insertRecordToTable(Tables.TBL_REQUESTS.name().toLowerCase(),newRequest);
        //update vacation
        String[] fields = {newRequest.getR_ID(),newRequest.getR_seller(),"","","","",
                "","","","","","",""};
        String vacation = sql.selectFromTable(Tables.TBL_VACATIONS,fields);
        if(vacation!=""){
            Vacation forUpdate = new Vacation(vacation);
            forUpdate.set_vacationStatus(Vacation.VacationStatus.IN_PROGRESS);
            sql.updateRecord(forUpdate);
        }
    }
}
