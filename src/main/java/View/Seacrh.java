package View;

import Controller.SearchController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Seacrh {
    @FXML
    public SearchController searchController;
    @FXML
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button updateButton;
    public javafx.scene.control.TextField searchText;

    public Seacrh() {};

    public Seacrh(SearchController searchController) {

        this.searchController = searchController;
    }

    public void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void setController(SearchController searchController)
    {
        this.searchController = searchController;
    }

    public void updateFormWindow(ActionEvent actionEvent) {

        Alert alert;
        String massege = searchController.getMassageAfterChange(searchText.getText(), false);
        if(massege.startsWith("User does")){
            alert = new  Alert(Alert.AlertType.ERROR);
            alert.setContentText(massege);
        }
        else{
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User detalis:\n\n" + massege);
        }
        alert.show();
    }




}
