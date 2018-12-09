package Controller;

import Model.ISQLable;
import Model.SQLModel;
import Model.User;

import java.util.Date;

public class UpdateFormController {
    SQLModel sqlModel;

    public UpdateFormController(){
        this.sqlModel = SQLModel.GetInstance();
    }
    public void UpdateUser(String username, String pwd, Date birthday, String privateName, String lastName,
                      String city, String bankAcount, String creditCard, String id){
        ISQLable newUser = new User(username, pwd, birthday, privateName
                , lastName, city, bankAcount, creditCard, id);
        sqlModel.updateRecord(newUser);
    }

}
