package sample;

import Model.ISQLable;
import Model.SQLModel;
import Model.User;
import javafx.scene.control.Alert;
import javafx.stage.Stage;



public class DeleteController {

    public SQLModel sqlModel;
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button deleteButton;
    public javafx.scene.control.TextField userText;

    public DeleteController() {}

    public DeleteController(SQLModel sqlModel) {
        this.sqlModel = sqlModel;
    }

    public void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void setSqlModel(SQLModel sqlModel) {
        this.sqlModel = sqlModel;
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