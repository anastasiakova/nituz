package View;

import Controller.DeleteController;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Delete {
    public DeleteController deleteController;
    //public SQLModel sqlModel;
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button deleteButton;
    public javafx.scene.control.TextField userText;

    public Delete() {}

//    public Delete(SQLModel sqlModel) {
//        this.sqlModel = sqlModel;
//    }
    public Delete(DeleteController deleteController) {
        this.deleteController = deleteController;
    }

    public void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

//    public void setSqlModel(SQLModel sqlModel) {
//        this.sqlModel = sqlModel;
//    }
    public void setController(DeleteController deleteController)
    {
        this.deleteController = deleteController;
    }

    public void deleteButtonAction(){
        ISQLable userToDelete = new User(userText.getText(),null,null,null
                ,null,null);
        sqlModel.deleteRecordFromTable(userToDelete);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("User deleted successfully");
        alert.show();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


}