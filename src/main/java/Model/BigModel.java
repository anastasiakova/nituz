package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    //users requests
    public static ObservableList<VactaionAndRequest> getRequestsPerUser(String logedInUsername) {
        String[] fields = new String[TblFields.enumDict.get("requestTblFields").size()];
        fields[1] = logedInUsername;
        String[] allRequests = SQLModel.GetInstance().selectFromTable(Tables.TBL_REQUESTS, fields).split("\n");
        List<VactaionAndRequest> vactaionAndRequests = new ArrayList<>();
        for (int i = 0; i < allRequests.length & allRequests[0] != ""; i++) {
            Request req = new Request(allRequests[i]);
            String vecationID = req.getVacationID();
            fields = new String[TblFields.enumDict.get("vacationFields").size()];
            fields[0] = vecationID;
            fields[10] = Vacation.VacationStatus.IN_PROGRESS.name();
            String reqVacation = SQLModel.GetInstance().selectFromTable(Tables.TBL_VACATIONS, fields);
            if (reqVacation != "") {
                Vacation vac = new Vacation(reqVacation);
                DateFormat formatter = new SimpleDateFormat("dd/mm/yyyy-HH:mm");
                vactaionAndRequests.add(new VactaionAndRequest(new SimpleDateFormat("dd/mm/yyyy-HH:mm:ss").format(vac.get__startDate()),
                        new SimpleDateFormat("dd/mm/yyyy-HH:mm:ss").format(vac.get_endDate()), vac.get_destination(), logedInUsername, req.getR_answer()));
            }

        }
        return FXCollections.observableList(vactaionAndRequests);
    }


        public static ObservableList<VactaionAndRequest> getRequestedVacation(String logedInUsername){
            String[] fields = new String[TblFields.enumDict.get("requestTblFields").size()];
            fields[1] = logedInUsername;
            fields[4] = "pending";
            String[] allRequests = SQLModel.GetInstance().selectFromTable(Tables.TBL_REQUESTS, fields).split("\n");
            List<VactaionAndRequest> vactaionAndRequests = new ArrayList<>();
            for (int i = 0; i < allRequests.length & allRequests[0] != ""; i++) {
                Request req = new Request(allRequests[i]);
                String vecationID = req.getVacationID();
                fields = new String[TblFields.enumDict.get("vacationFields").size()];
                fields[0] = vecationID;
                String reqVacation = SQLModel.GetInstance().selectFromTable(Tables.TBL_VACATIONS, fields);
                if (reqVacation != "") {
                    Vacation vac = new Vacation(reqVacation);
                    DateFormat formatter = new SimpleDateFormat("dd/mm/yyyy-HH:mm");
                    vactaionAndRequests.add(new VactaionAndRequest(new SimpleDateFormat("dd/mm/yyyy-HH:mm:ss").format(vac.get__startDate()),
                            new SimpleDateFormat("dd/mm/yyyy-HH:mm:ss").format(vac.get_endDate()), vac.get_destination(), logedInUsername, req.getR_answer()));
                }
            }
            return FXCollections.observableList(vactaionAndRequests);
    }
}
