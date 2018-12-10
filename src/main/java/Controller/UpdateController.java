package Controller;

import Model.*;
import javafx.scene.control.Alert;

import java.util.Date;

public class UpdateController {
    SQLModel sqlModel;

    public UpdateController(){
        this.sqlModel = SQLModel.GetInstance();
    }

    public String UpdateUser(String username){
        String[]users = new String[TblFields.enumDict.get("userFields").size()];
        users[0] = username;
        String ansSelect = sqlModel.selectFromTable(Tables.TBL_USERS,users);
        if(ansSelect == "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("User does not exist in the system! ");
            alert.show();
        }
        return ansSelect;
    }

}
