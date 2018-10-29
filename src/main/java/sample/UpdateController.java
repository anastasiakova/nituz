package sample;

import Model.SQLModel;
import Model.Tables;
import Model.UserTblFields;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateController {

    @FXML
    private AnchorPane content;
    public String ans;
    public SQLModel sqlModel;
    @FXML
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button updateButton;
    public javafx.scene.control.TextField searchText;

    public javafx.scene.control.TextField userText;


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
        try {
            String[]users = new String[UserTblFields.values().length];
            users[0] = searchText.getText();
            ans = sqlModel.selectFromTable(Tables.TBL_USERS,users);
            Stage stage = new Stage();
            stage.setTitle("UpdateForm User");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("/UpdateForm.fxml").openStream());
            UpdateFormController updateFormController = fxmlLoader.getController();
            updateFormController.setSqlModel(sqlModel);
            Scene scene = new Scene(root, 250, 220);
            System.out.println(ans);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {

        }
    }
}
