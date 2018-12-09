package Controller;
import Model.*;

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
    public String getUserCreatedMassage(String userText,Boolean withPssword){
        return new SearchController().getMassageAfterChange(userText,withPssword);
    }

}
