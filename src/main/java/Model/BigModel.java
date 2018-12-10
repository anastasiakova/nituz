package Model;

public class BigModel {

    public static void CreatePaymentAndUpdateVacation(Payments newPayment){

        //sql.insertRecordToTable(Tables.TBL_PAYMENTS.toString().toLowerCase(), newPayment);
        //String approvedReqId = newPayment.get_aprovedRequest();
       // Request
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
