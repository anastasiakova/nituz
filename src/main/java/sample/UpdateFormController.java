package sample;

import Model.SQLModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UpdateFormController {


    public SQLModel sqlModel;
    @FXML
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button createButton;

    public javafx.scene.control.TextField userText;


    public UpdateFormController() {};

    public UpdateFormController(SQLModel sqlModel) {
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

    public void updateFormWindow(ActionEvent actionEvent) {
    }
}
