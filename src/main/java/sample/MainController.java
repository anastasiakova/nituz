package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainController {
    public javafx.scene.control.Button btn_create;


    public void createWindow(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Create User");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("/Create.fxml").openStream());
            Scene scene = new Scene(root, 270, 420);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {

        }
    }

}
