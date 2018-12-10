package View;

import Controller.SearchController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.transform.sax.SAXSource;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;


public class Login {

    //public SQLModel sqlModel;
    public SearchController searchController;
    @FXML
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button logIN;
    public TextField userText;
    public TextField passText;
    public openWindowsController parentController;

    public Login(){};

    //    public Create(SQLModel sqlModel) {
//        this.sqlModel = sqlModel;
//    }
    public Login(SearchController searchController) {
        this.searchController = searchController;
    }

    public void setParentController(openWindowsController parentController) {
        this.parentController = parentController;
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

    public void setController(SearchController searchController) {
        this.searchController = searchController;
    }

    public void logINButtonAction(){
        //validate user name & password
        boolean loginSuccessful = false;
        if(userText.getText() != "" && passText.getText() != ""){
            System.out.println(userText.getText());
            //controller search
            loginSuccessful = searchController.isLoginValid(userText.getText(), passText.getText());
            if(loginSuccessful) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Login successful");

                this.parentController.isLoggedIn=true;
                System.out.println(this.parentController.isLoggedIn);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("You Suck!");
                alert.show();
            }
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }

        //successes
        //fail
    }



}
