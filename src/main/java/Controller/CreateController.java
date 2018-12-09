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

    public String getUserCreatedMassage(String userText) {
        String[] users = new String[TblFields.enumDict.get("userFields").size()];
        users[0] = userText;
        String ansSelect = sqlModel.selectFromTable(Tables.TBL_USERS, users);
        String[] arrAns = ansSelect.split(",");
        return "User Created:\n\n" +
                        "User Name: " + arrAns[0] + "\n"
                        + "Password: " + arrAns[1].substring(1)

                        + "\nB-day: " + arrAns[2].substring(1)
                        + "\nFirst Name: " + arrAns[3].substring(1)
                        + "\nLast Name: " + arrAns[4].substring(1)
                        + "\nCity: " + arrAns[5].substring(1, arrAns[5].length() - 2);
    }
}
