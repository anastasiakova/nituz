package View;

import Controller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainView {
    //public SQLModel sqlModel;
    public MainView(){
    };

//    public MainView(SQLModel sqlModel) {
//        this.sqlModel = sqlModel;
//    }

//    public void setSqlModel(SQLModel sqlModel) {
//        this.sqlModel = sqlModel;
//    }

    public javafx.scene.control.Button btn_create;
    public javafx.scene.control.Button btn_update;



    public void createWindow(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Create User");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("/Create.fxml").openStream());
            Create creatView = fxmlLoader.getController();
            creatView.setCreateController(new CreateController());
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
            Delete deleteView = fxmlLoader.getController();
            deleteView.setController(new DeleteController());
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
            Update creatView = fxmlLoader.getController();
            creatView.setController(new UpdateController());
            Scene scene = new Scene(root, 250, 220);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {

        }
    }


    public void searchWindow(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Search User User");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("/Sreach.fxml").openStream());
            Seacrh creatView = fxmlLoader.getController();
            creatView.setController(new SearchController());
            Scene scene = new Scene(root, 250, 220);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {

        }
    }


}
