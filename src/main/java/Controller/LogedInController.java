package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogedInController {

    SQLModel sqlModel;
    User loged;

    public LogedInController() {
        this.sqlModel = SQLModel.GetInstance();

    }

    public boolean tryLogIn(String username,String pwd){
        String[] fields = new String[TblFields.enumDict.get("userFields").size()];
        fields[0] = username;
        fields[1] = pwd;
        String user = sqlModel.selectFromTable(Tables.TBL_USERS, fields);
        if(user.equals("")){
            return false;
        }
        String[] valid = user.split("\n");
        loged = new User(valid[0]);
        return true;
    }

    public ObservableList<VactaionAndRequest> getMyRequests(){
//        String[] fields = new String[TblFields.enumDict.get("requestTblFields").size()];
//        fields[1] = logedInUsername;
//        String[] allRequests = SQLModel.GetInstance().selectFromTable(Tables.TBL_REQUESTS, fields).split("\n");
        ArrayList<Request> allRequests = loged.getMyRequests();
        List<VactaionAndRequest> vactaionAndRequests = new ArrayList<>();
        for (int i = 0; i < allRequests.size(); i++) {
            Request req = allRequests.get(i);
            Vacation vac = req.getVacation();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
            Date d;
            try {
                 d = formatter.parse(vac.get__startDate());
                if (d.after(new Date())) {
                    vactaionAndRequests.add(new VactaionAndRequest(vac.get__startDate(), vac.get_endDate(),
                            vac.get_destination(), vac.get_ownerID(), req.getR_answer(),req.getR_ID()));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return FXCollections.observableList(vactaionAndRequests);
    }

    public ObservableList<VactaionAndRequest> getToAnswerRequests(){
        ArrayList<Request> allRequests = loged.getRequestsForMe();
        List<VactaionAndRequest> vactaionAndRequests = new ArrayList<>();
        for (int i = 0; i < allRequests.size(); i++) {
            Request req = allRequests.get(i);
            if(!allRequests.get(i).getR_answer().equals("confirmed") && !allRequests.get(i).getR_answer().equals("rejected")){
                Vacation vac = req.getVacation();
                vactaionAndRequests.add(new VactaionAndRequest(vac.get__startDate(), vac.get_endDate(),
                        vac.get_destination(), req.getR_buyerID(), req.getR_answer(),req.getR_ID()));
            }
        }
        return FXCollections.observableList(vactaionAndRequests);
    }

    public void deleteUser(){
        ISQLable userToDelete = new User(loged.getUsername(), "",null,"","",
                "","");
        sqlModel.deleteRecordFromTable(userToDelete);
        loged = null;
    }

    public void UpdateUser( String pwd, Date birthday, String privateName, String lastName,
                           String city, String id){
        loged = new User(loged.getUsername(), pwd, birthday, privateName, lastName, city, id);
        sqlModel.updateRecord(loged);
    }

    public void CreateVacation(Date __startDate, Date _endDate,
                               String _destination, String _aviationCompany,
                               int _numOfTickets, Vacation.TicketType _ticketType,
                               boolean _isBaggageIncluded, boolean _isRoundTrip,
                               Vacation.VacationType _vacationType, Vacation.VacationStatus _vacationStatus,
                               Vacation.VacationSleepingArrangements _vacationSleepingArrangements, String _ownerID){
        ISQLable newVacation = new Vacation(loged.getUsername(),__startDate, _endDate, _destination, _aviationCompany, _numOfTickets, _ticketType, _isBaggageIncluded, _isRoundTrip, _vacationType, _vacationStatus, _vacationSleepingArrangements, _ownerID);
        sqlModel.insertRecordToTable(Tables.TBL_VACATIONS.toString().toLowerCase(), newVacation);
    }

    public void CreateRequestAndUpdateVacation( String r_seller, String vacationID) {
        Request newRequest = new Request(loged.getUsername(), r_seller, vacationID);
        //create request
        sqlModel.insertRecordToTable(Tables.TBL_REQUESTS.name().toLowerCase(),newRequest);
        loged.UpdateMyRequsts(newRequest);
        //update vacation
        newRequest.getVacation().set_vacationStatus(Vacation.VacationStatus.IN_PROGRESS);
        sqlModel.updateRecord(newRequest.getVacation());
    }

    public void CreatePaymentAndUpdateVacation(String aprovedRequestId){
        Payments newPayment = new Payments(aprovedRequestId);
        newPayment.ChangeStatus(false);
        newPayment.get_Request().setPayment(newPayment);
        loged.UpdateMyRequsts(newPayment.get_Request());
        sqlModel.updateRecord(newPayment.get_Request());
        newPayment.get_Request().getVacation().set_vacationStatus(Vacation.VacationStatus.SOLD);
        sqlModel.updateRecord(newPayment.get_Request().getVacation());
        //Insert Payment to DB
        sqlModel.insertRecordToTable(Tables.TBL_PAYMENTS.name().toLowerCase(), newPayment);

        //Set request as approved:
        //String[] requestFields = new String[TblFields.enumDict.get("requestTblFields").size()];
        //String approvedReqId = newPayment.get_aprovedRequest();
        //requestFields[0] = approvedReqId;
        //approvedRequest.setPayment(newPayment);
        //Request approvedRequest = new Request(sqlModel.selectFromTable(Tables.TBL_REQUESTS, requestFields));
        //approvedRequest.setR_answer("Approved");
        //Set vacation as sold:
//        String[] vacationFields = new String[TblFields.enumDict.get("vacationFields").size()];
//        String vacationId = approvedRequest.getVacationID();
//        vacationFields[0] = vacationId;

        //Vacation vacation = new Vacation(sqlModel.selectFromTable(Tables.TBL_VACATIONS, vacationFields));
        // vacation.set_vacationStatus(Vacation.VacationStatus.SOLD);
    }

    public ObservableList<Vacation> getAllAvailableVacations(){
        String[] fields = new String[TblFields.enumDict.get("vacationFields").size()];
        fields[10] = Vacation.VacationStatus.FOR_SALE.name().toLowerCase();

        String[] allVacationsStr = sqlModel.selectFromTable(Tables.TBL_VACATIONS, fields).split("\n");
        List<Vacation> vacations = new ArrayList<Vacation>();
        for (int i = 0; i < allVacationsStr.length & !allVacationsStr[0].equals(""); i++) {
            Vacation vac = new Vacation(allVacationsStr[i]);
            vacations.add(vac);
        }
        return FXCollections.observableList(vacations);
    }

    public void UpdateRequest(String status, String reqID){
       List<Request> myRequests = loged.getRequestsForMe();
        for (Request req: myRequests) {
            if(req.getR_ID().equals(reqID)){
                req.setR_answer(status);
                if(status.equals("rejected")) {
                    Vacation vac = req.getVacation();
                    vac.set_vacationStatus(Vacation.VacationStatus.FOR_SALE);
                    sqlModel.updateRecord(vac);
                }
                sqlModel.updateRecord(req);
            }
        }
    }

    public void CreateVacation(String __startDate, String _endDate,
                               String _destination, String _aviationCompany,
                               int _numOfTickets, String _ticketType,
                               boolean _isBaggageIncluded, boolean _isRoundTrip,
                               String _vacationType, String _vacationSleepingArrangements){
        ISQLable newVacation = new Vacation(__startDate, _endDate, _destination, _aviationCompany,
                _numOfTickets, _ticketType, _isBaggageIncluded, _isRoundTrip, _vacationType,
                 _vacationSleepingArrangements, loged.getUsername());
        sqlModel.insertRecordToTable(Tables.TBL_VACATIONS.toString().toLowerCase(), newVacation);
    }

    public  void LogOut(){
        loged = null;
    }

    public String getUserNameFromUserAsStripAndCleanString(){
        return this.loged.getUsername();
    }

    public ObservableList<Vacation> CreateSwitchVacation(){
        String[] fields = new String[TblFields.enumDict.get("vacationFields").size()];
        fields[10] = Vacation.VacationStatus.FOR_SALE.name().toLowerCase();

        String[] allVacationsStr = sqlModel.selectFromTable(Tables.TBL_VACATIONS, fields).split("\n");
        List<Vacation> vacations = new ArrayList<Vacation>();
        for (int i = 0; i < allVacationsStr.length & !allVacationsStr[0].equals(""); i++) {
            Vacation vac = new Vacation(allVacationsStr[i]);
            if(vac.get_ownerID().equals(loged.getUsername())){
                vacations.add(vac);
            }
        }
        return FXCollections.observableList(vacations);
    }


}
