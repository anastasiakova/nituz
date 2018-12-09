package Controller;
import Model.*;

public class CreateController {
    SQLModel sqlModel;

    public CreateController(){
        this.sqlModel = SQLModel.GetInstance();
    }

    public CreateUser(){
        ISQLable newUser = new User(userText.getText(), passText.getText(), date, fNameText.getText()
                        , lNameText.getText(), cityText.getText());
        sqlModel.insertRecordToTable(Tables.TBL_USERS.toString().toLowerCase(), newUser);
    }

}
