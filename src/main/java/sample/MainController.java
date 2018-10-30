package sample;

import Model.SQLModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainController {
    public SQLModel sqlModel;

    public MainController(){};
    public MainController(SQLModel sqlModel) {
        this.sqlModel = sqlModel;
    }

    public void setSqlModel(SQLModel sqlModel) {
        this.sqlModel = sqlModel;
    }

    public javafx.scene.control.Button btn_create;
    public javafx.scene.control.Button btn_update;



    public void createWindow(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Create User");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("/Create.fxml").openStream());
            CreateController creatView = fxmlLoader.getController();
            creatView.setSqlModel(sqlModel);
            Scene scene = new Scene(root, 270, 420);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {

        }
    }
    public void deleteWindow(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Delete User");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("/Delete.fxml").openStream());
            DeleteController deleteView = fxmlLoader.getController();
            deleteView.setSqlModel(sqlModel);
            Scene scene = new Scene(root, 250, 220);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {

        }
    }

    public void updateWindow(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Update User");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("/Update.fxml").openStream());
            UpdateController creatView = fxmlLoader.getController();
            creatView.setSqlModel(sqlModel);
            Scene scene = new Scene(root, 400, 420);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {

        }
    }


}
