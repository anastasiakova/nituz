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
        ISQLable newUser = new User(username, pwd, birthday, privateName, lastName, city, bankAcount, id);
        sqlModel.insertRecordToTable(Tables.TBL_USERS.toString().toLowerCase(), newUser);
    }
    public String getUserCreatedMassage(String userText,Boolean withPassword){
        return new SearchController().getMassageAfterChange(userText,withPassword);
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
