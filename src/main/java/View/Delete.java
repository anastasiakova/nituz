package View;

import Controller.DeleteController;
import Controller.LogedInController;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Delete {
    public LogedInController logedInController;
    //public SQLModel sqlModel;
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button deleteButton;
    public javafx.scene.control.TextField userText;

    public Delete() {}

//    public Delete(SQLModel sqlModel) {
//        this.sqlModel = sqlModel;
//    }

    public void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

//    public void setSqlModel(SQLModel sqlModel) {
//        this.sqlModel = sqlModel;
//    }
    public void setController(LogedInController logedInController)
    {
        this.logedInController = logedInController;
    }

    public void deleteUserButtonAction(){
        String username = userText.getText();
        this.logedInController.deleteUser();
//        ISQLable userToDelete = new User(username, null,null,null,null,
//                null,null,null,null);
        //sqlModel.deleteRecordFromTable(userToDelete);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("User deleted successfully");
        alert.show();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


}