package Controller;

import Model.ISQLable;
import Model.SQLModel;
import Model.User;

public class DeleteController {
    public static String currentlyLoggedInUserID = ""; //TODO find a better solution!
    SQLModel sqlModel;


    public DeleteController(){
        this.sqlModel = SQLModel.GetInstance();
    }

    public void deleteUser(String username){
        ISQLable userToDelete = new User(username, "",null,"",
                "","","","");
        sqlModel.deleteRecordFromTable(userToDelete);
    }
}
