package Controller;

import Model.ISQLable;
import Model.SQLModel;
import Model.User;

public class DeleteController {

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
