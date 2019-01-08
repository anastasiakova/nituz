package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
       SearchController search = new SearchController();
       boolean ans = search.isLoginValid(username,pwd);
       if (ans){
           loged = new User(username);
       }
       return ans;
    }

    public ObservableList<VactaionAndRequest> getMyRequests(boolean isSwitch){

        ArrayList<Request> allRequests = loged.getMyRequests();
        List<VactaionAndRequest> vactaionAndRequests = new ArrayList<>();
        for (int i = 0; i < allRequests.size(); i++) {
            Request req = allRequests.get(i);
            Vacation vac = req.getVacation();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
            Date d;
            String[] fieldsOfTradeWhenLogedIsInitlizer = {"", loged.getUsername(), req.getR_seller(), "", req.getVacation().get_vacationID()};
            String tradeWhenLogedIsInitlizer = sqlModel.selectFromTable(Tables.TBL_TRADES, fieldsOfTradeWhenLogedIsInitlizer);

            String[]fieldsOfTradeWhenOtherIsInitlizer = {"",req.getR_seller(), loged.getUsername(), req.getVacation().get_vacationID(), "" };
            String tradeWhenOtherIsInitlizer = sqlModel.selectFromTable(Tables.TBL_TRADES, fieldsOfTradeWhenOtherIsInitlizer);
            try {
                 d = formatter.parse(vac.get__startDate());
                if (d.after(new Date())) {
                    if(isSwitch){

                        if(tradeWhenLogedIsInitlizer != "" ){

                            vactaionAndRequests.add(new VactaionAndRequest(vac.get__startDate(), vac.get_endDate(),
                                    vac.get_destination(), vac.get_ownerID(), req.getR_answer(), req.getR_ID()));
                        }
                    }
                    else{
                        if(tradeWhenOtherIsInitlizer == "" && tradeWhenLogedIsInitlizer == "" ) {
                            vactaionAndRequests.add(new VactaionAndRequest(vac.get__startDate(), vac.get_endDate(),
                                    vac.get_destination(), vac.get_ownerID(), req.getR_answer(), req.getR_ID()));
                        }
                    }

                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return FXCollections.observableList(vactaionAndRequests);
    }

    public ObservableList<VactaionAndRequest> getToAnswerRequests(boolean isSwitch){
        ArrayList<Request> allRequests = loged.getRequestsForMe();
        List<VactaionAndRequest> vactaionAndRequests = new ArrayList<>();
        for (int i = 0; i < allRequests.size(); i++) {
            Request req = allRequests.get(i);
            if(!allRequests.get(i).getR_answer().equals("confirmed") && !allRequests.get(i).getR_answer().equals("rejected")){
                Vacation vac = req.getVacation();
                String[] fieldsOfTradeWhenLogedIsInitlizer = {"",req.getR_buyerID(),loged.getUsername(),"",req.getVacation().get_vacationID()};
                String tradeOfTradeWhenLogedIsInitlizer = sqlModel.selectFromTable(Tables.TBL_TRADES, fieldsOfTradeWhenLogedIsInitlizer);

                String[] fieldsOfTradeWhenOtherIsInitlizer = {"",loged.getUsername(),req.getR_buyerID(),req.getVacation().get_vacationID(),"" };
                String tradeOfTradeWhenOtherIsInitlizer = sqlModel.selectFromTable(Tables.TBL_TRADES, fieldsOfTradeWhenOtherIsInitlizer);

                if(isSwitch){

                    if(tradeOfTradeWhenLogedIsInitlizer != "" ) {
                        vactaionAndRequests.add(new VactaionAndRequest(vac.get__startDate(), vac.get_endDate(),
                                vac.get_destination(), req.getR_buyerID(), req.getR_answer(), req.getR_ID()));
                    }
                }
                else{
                    if(tradeOfTradeWhenLogedIsInitlizer == "" && tradeOfTradeWhenOtherIsInitlizer == "" ) {
                        vactaionAndRequests.add(new VactaionAndRequest(vac.get__startDate(), vac.get_endDate(),
                                vac.get_destination(), req.getR_buyerID(), req.getR_answer(),req.getR_ID()));
                    }
                }

            }
        }
        return FXCollections.observableList(vactaionAndRequests);
    }

    public void UpdateUser( String pwd, Date birthday, String privateName, String lastName,
                           String city, String id){
        loged = new User(loged.getUsername(), pwd, birthday, privateName, lastName, city, id);
        sqlModel.updateRecord(loged);
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

    public ObservableList<Vacation> getAllAvailableVacations(){
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

//    public Vacation getAvailableVacation(String reqId){
//        //get request
//        String[] requestFields = new String[TblFields.enumDict.get("requestTblFields").size()];
//        requestFields[0] = reqId;
//        String request = sqlModel.selectFromTable(Tables.TBL_REQUESTS, requestFields);
//        Request myRequest = new Request(request);
//
//        //get trade
//        String[] fieldsOfTrade = {"", myRequest.getR_buyerID(), loged.getUsername(), "", myRequest.getVacation().get_vacationID()};
//        String trade = sqlModel.selectFromTable(Tables.TBL_TRADES, fieldsOfTrade);
//        String [] splittedTrade = trade.split("\n")[0].split(", ");
//
//        String[] fieldsOfVacation = new String[TblFields.enumDict.get("vacationFields").size()];
//        fieldsOfVacation[0] = splittedTrade[3];
//
//        String[] allVacationsStr = sqlModel.selectFromTable(Tables.TBL_VACATIONS, fieldsOfVacation).split("\n");
//
//        Vacation vac = new Vacation(allVacationsStr[0]);
//
//        return vac;
//    }

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
                if(status.equals("confirmed")) {
                    Vacation vac = req.getVacation();
                    vac.set_vacationStatus(Vacation.VacationStatus.SOLD);
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

    public void deleteUser(){
        loged = null;
    }

    public void UpdateSwitchVacation(String status, String reqID){
        //update my req for me and other user vacation
        this.UpdateRequest(status,reqID);

        //get request
        String[] requestFields = new String[TblFields.enumDict.get("requestTblFields").size()];
        requestFields[0] = reqID;
        String request = sqlModel.selectFromTable(Tables.TBL_REQUESTS, requestFields);
        Request myRequest = new Request(request);

        //get trade
        String[] fields = {"", myRequest.getR_buyerID(), loged.getUsername(), "", myRequest.getVacation().get_vacationID()};
        String trade = sqlModel.selectFromTable(Tables.TBL_TRADES, fields);
        String [] splittedTrade = trade.split("\n")[0].split(", ");

        //get other user
        String[] otherUserFields = new String[TblFields.enumDict.get("userFields").size()];
        otherUserFields[0] = splittedTrade[1];//otherUserVacation.get_ownerID() ;
        String user = sqlModel.selectFromTable(Tables.TBL_USERS, otherUserFields);
        String[] valid = user.split("\n");
        User otherUser = new User(valid[0]);

        //update other user req and my vacation
        List<Request> myRequests = otherUser.getRequestsForMe();
        for (Request req: myRequests) {
            if(req.getVacation().get_vacationID().equals(splittedTrade[3])){
                if(status.equals("rejected")) {
                    Vacation vac = req.getVacation();
                    vac.set_vacationStatus(Vacation.VacationStatus.FOR_SALE);
                    sqlModel.updateRecord(vac);
                    sqlModel.deleteRecordFromTable(req);
                }
                if(status.equals("confirmed")) {
                    req.setR_answer(status);
                    Vacation vac = req.getVacation();
                    vac.set_vacationStatus(Vacation.VacationStatus.SOLD);
                    sqlModel.updateRecord(vac);
                    sqlModel.updateRecord(req);
                }

            }
        }

        //update trade(delete)
        if( !status.equals("set_meeting")){
            String[] data = {"", otherUser.getUsername(), loged.getUsername(),splittedTrade[3] , myRequest.getVacation().get_vacationID()};
            sqlModel.deleteRecordFromTable("tbl_trades", TblFields.enumDict.get("tradeFields"),data );
            String[] newLogedIn = {loged.getUsername(),loged.getPwd(),loged.getBirthday(),loged.getPrivateName(),loged.getLastName(),loged.getCity(),loged.getId()};
            loged = new User(newLogedIn);
        }

    }

    public void CreateSwitchVacation(String askedVacId, String MyVacId){

        String[] fields = new String[TblFields.enumDict.get("vacationFields").size()];
        fields[0] = askedVacId;
        Vacation askedVac = new Vacation( sqlModel.selectFromTable(Tables.TBL_VACATIONS, fields).split("\n")[0]);

        //create requests
        this.CreateRequestAndUpdateVacation(askedVac.get_ownerID(),askedVac.get_vacationID());//updates asked vac

        Request newRequest = new Request(askedVac.get_ownerID(), loged.getUsername(), MyVacId);
        sqlModel.insertRecordToTable(Tables.TBL_REQUESTS.name().toLowerCase(),newRequest);

        //update vacation
        newRequest.getVacation().set_vacationStatus(Vacation.VacationStatus.IN_PROGRESS);
        sqlModel.updateRecord(newRequest.getVacation()); //updates my vac

        //put new trade in trades
        String fieldsNames = "tbl_trades("
                + TblFields.enumDict.get("tradeFields").get(0) + ", "
                + TblFields.enumDict.get("tradeFields").get(1) + ", "
                + TblFields.enumDict.get("tradeFields").get(2) + ", "
                + TblFields.enumDict.get("tradeFields").get(3) + ", "
                + TblFields.enumDict.get("tradeFields").get(4)
                + ") VALUES(?,?,?,?,?)";
        String[] data = {MyVacId+askedVacId,loged.getUsername(), askedVac.get_ownerID(), MyVacId, askedVacId};
        sqlModel.insertRecordToTable(fieldsNames,data);
    }

    public void CreatePaymentAndUpdateVacation(String aprovedRequestId){

    }

    public Vacation CreateVacation(String reqId){
        //get request
        String[] requestFields = new String[TblFields.enumDict.get("requestTblFields").size()];
        requestFields[0] = reqId;
        String request = sqlModel.selectFromTable(Tables.TBL_REQUESTS, requestFields);
        Request myRequest = new Request(request);

        //get trade
        String[] fieldsOfTrade = {"", myRequest.getR_buyerID(), loged.getUsername(), "", myRequest.getVacation().get_vacationID()};
        String trade = sqlModel.selectFromTable(Tables.TBL_TRADES, fieldsOfTrade);
        String [] splittedTrade = trade.split("\n")[0].split(", ");

        String[] fieldsOfVacation = new String[TblFields.enumDict.get("vacationFields").size()];
        fieldsOfVacation[0] = splittedTrade[3];

        String[] allVacationsStr = sqlModel.selectFromTable(Tables.TBL_VACATIONS, fieldsOfVacation).split("\n");

        Vacation vac = new Vacation(allVacationsStr[0]);

        return vac;
    }

    public String getUserNameFromUserAsStripAndCleanString(){
        return this.loged.getUsername();
    }
}
