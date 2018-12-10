package Controller;
import Model.*;
import Model.Vacation.*;
import java.util.Date;

public class CreateController {
    SQLModel sqlModel;

    public CreateController(){
        this.sqlModel = SQLModel.GetInstance();
    }

    public void CreateUser(String username, String pwd, Date birthday, String privateName, String lastName, String city, String bankAcount, String creditCard, String id){
        ISQLable newUser = new User(username, pwd, birthday, privateName, lastName, city, bankAcount, creditCard, id);
        sqlModel.insertRecordToTable(Tables.TBL_USERS.toString().toLowerCase(), newUser);
    }
    public String getUserCreatedMassage(String userText,Boolean withPassword){
        return new SearchController().getMassageAfterChange(userText,withPassword);
    }

    public void CreateVacation(Date __startDate, Date _endDate,
                               String _destination, String _aviationCompany,
                               int _numOfTickets, TicketType _ticketType,
                               boolean _isBaggageIncluded, boolean _isRoundTrip,
                               VacationType _vacationType, VacationStatus _vacationStatus,
                               VacationSleepingArrangements _vacationSleepingArrangements, String _ownerID){
        ISQLable newVacation = new Vacation(__startDate, _endDate, _destination, _aviationCompany, _numOfTickets, _ticketType, _isBaggageIncluded, _isRoundTrip, _vacationType, _vacationStatus, _vacationSleepingArrangements, _ownerID);
        sqlModel.insertRecordToTable(Tables.TBL_VACATIONS.toString().toLowerCase(), newVacation);
    }

    public void CreatePayment(String aprovedRequestId){
        Payments newPayment = new Payments(aprovedRequestId);
        BigModel.CreatePaymentAndUpdateVacation(newPayment);
    }

    public void CreateRequest(String r_buyer, String r_seller, String vacationID){
        Request newRequest = new Request(r_buyer, r_seller, vacationID);
        BigModel.CreateRequestAndUpdateVacation(newRequest);
    }

}
