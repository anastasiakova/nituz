package sample;

import Model.SQLModel;
import Model.Tables;
import Model.UserTblFields;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateController {

    @FXML
    //private AnchorPane content;
    //public static String ans;
    public SQLModel sqlModel;
    @FXML
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button updateButton;
    public javafx.scene.control.TextField searchText;
    //public javafx.scene.control.TextField userText;

    public UpdateController() {};


    public UpdateController(SQLModel sqlModel) {
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
        String[]users = new String[UserTblFields.values().length];
        users[0] = searchText.getText();
        String ansSelect = sqlModel.selectFromTable(Tables.TBL_USERS,users);
        String[]arrAns = ansSelect.split(",");
        if(ansSelect == "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("User does not exist in the system! ");
            alert.show();
        }

        try {
            Stage stage = new Stage();
            stage.setTitle("Update User");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("/UpdateForm.fxml").openStream());
            UpdateFormController ViewForm = fxmlLoader.getController();
            ViewForm.setSqlModel(sqlModel);
            Scene scene = new Scene(root, 400, 550);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            ViewForm.userText.setText(searchText.getText());
            ViewForm.passText.setText(arrAns[1].substring(1));
            ViewForm.fNameText.setText(arrAns[3].substring(1));
            ViewForm.lNameText.setText(arrAns[4].substring(1));

            //ViewForm.dateText.
            ViewForm.cityText.setText(arrAns[5].substring(1,arrAns[5].length()-2));
                stage.show();
            } catch (Exception e) {

            }


    }
}
