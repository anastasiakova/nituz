package View;

import Controller.*;
import Model.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Date;
import java.util.Observable;

import Model.Vacation;
import Controller.SearchController;

import javax.swing.*;


public class openWindowsController {
    @FXML
    public Button resetButton;
    @FXML
    public Button loginButton;
    public Button logOutButton;
    public Button allMyRequestButton;
    public Button updateDetalisButton;
    public Button buyThisVacaionButton;
    public Button addVactionButton;

    public TextField userText;
    public TextField passText;
    public Label t1;
    public Label t2;
    public Label t3;
    public Label t4;

    public Label passLabel;
    public Label useLabel;
    public Label welcomeLabel;

    public openWindowsController() {
    }
    public openWindowsController(SearchController searchController) { this.searchController = searchController;}

    public SearchController searchController = new SearchController();
    public javafx.scene.control.Button btn_create;
    public javafx.scene.control.TableView<Vacation> vacTable;
    public javafx.scene.control.TableColumn<Vacation, String> startDate;
    public javafx.scene.control.TableColumn<Vacation, String> endDate;
    public javafx.scene.control.TableColumn<Vacation, String> destination;
    public javafx.scene.control.TableColumn<Vacation, String> aviationCompany;
    public javafx.scene.control.TableColumn<Vacation, String> numOfTickets;
    public javafx.scene.control.TableColumn<Vacation, String> ticketType;
    public javafx.scene.control.TableColumn<Vacation, String> isBaggageIncluded;
    public javafx.scene.control.TableColumn<Vacation, String> isRoundTrip;
    public javafx.scene.control.TableColumn<Vacation, String> vacationType;
    public javafx.scene.control.TableColumn<Vacation, String> vacationStatus;
    public javafx.scene.control.TableColumn<Vacation, String> vacationSleepingArrangements;
    public javafx.scene.control.TableColumn<Vacation, String> ownerID;
    public boolean isOk=false;


    public void initialize() {
        this.logOutButton.setVisible(false);
        this.welcomeLabel.setVisible(false);
        this.buyThisVacaionButton.setVisible(false);
        this.addVactionButton.setVisible(false);
        this.allMyRequestButton.setVisible(false);
        this.updateDetalisButton.setVisible(false);
        startDate.setCellValueFactory(new PropertyValueFactory<Vacation, String>("__startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<Vacation, String>("__endDate"));
        destination.setCellValueFactory(new PropertyValueFactory<Vacation, String>("__destination"));
        aviationCompany.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_aviationCompany"));
        numOfTickets.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_numOfTickets"));
        ticketType.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_ticketType"));
        isBaggageIncluded.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_isBaggageIncluded"));
        isRoundTrip.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_isRoundTrip"));
        vacationType.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_vacationType"));
        vacationStatus.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_vacationStatus"));
        vacationSleepingArrangements.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_vacationSleepingArrangements"));
        ownerID.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_ownerID"));

        SearchController listOfVactions = new SearchController();
        ObservableList<Vacation> data = listOfVactions.getAllAvailableVacations();
        vacTable.setItems(data);
        if(isOk)
            System.out.println("sddsfsdf");

    }

    public void logINButtonAction(ActionEvent event) throws IOException {
        //validate user name & password
        boolean loginSuccessful = false;
        if (userText.getText() != "" && passText.getText() != "") {
            System.out.println(userText.getText());
            //controller search

            loginSuccessful = searchController.isLoginValid(userText.getText(), passText.getText());
            if (loginSuccessful) {

//                isOk=true;
//                FXMLLoader fxmlControl = new FXMLLoader();
//                Parent lognView = FXMLLoader.load(getClass().getResource("/openWindow.fxml"));
//                Scene userView = new Scene(lognView);
//                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//                window.setScene(userView);
//
//                window.show();
                loginButtonsMaker(event);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("You Suck!");
                alert.show();
            }

        }
    }

    public void loginButtonsMaker(ActionEvent event) {
        this.resetButton.setVisible(false);
        this.userText.setVisible(false);
        this.loginButton.setVisible(false);
        this.passText.setVisible(false);
        this.useLabel.setVisible(false);
        this.passLabel.setVisible(false);
        this.logOutButton.setVisible(true);
        this.welcomeLabel.setText("Welcome "+this.userText.getText()+"!");
        this.welcomeLabel.setVisible(true);
        this.buyThisVacaionButton.setVisible(true);
        this.addVactionButton.setVisible(true);
        this.allMyRequestButton.setVisible(true);
        this.updateDetalisButton.setVisible(true);
        this.t1.setVisible(false);
        this.t2.setVisible(false);
        this.t3.setVisible(false);
        this.t4.setVisible(false);
    }

    public void logOut() {
        this.searchController = new SearchController();
        this.resetButton.setVisible(true);
        this.userText.setVisible(true);
        this.userText.setText("");
        this.passText.setText("");
        this.loginButton.setVisible(true);
        this.passText.setVisible(true);
        this.useLabel.setVisible(true);
        this.passLabel.setVisible(true);
        this.logOutButton.setVisible(false);
        this.welcomeLabel.setVisible(false);
        this.buyThisVacaionButton.setVisible(false);
        this.addVactionButton.setVisible(false);
        this.allMyRequestButton.setVisible(false);
        this.updateDetalisButton.setVisible(false);
        this.t1.setVisible(true);
        this.t2.setVisible(true);
        this.t3.setVisible(true);
        this.t4.setVisible(true);

    }

    public void unVisibleButtons(ActionEvent event){
        if(isOk){
            resetButton.setVisible(false);
        }
    }

    public void resetButtonAction(ActionEvent actionEvent) {
        this.userText.setText("");
        this.passText.setText("");
        searchController=null;
        searchController=new SearchController();
        resetButton.setVisible(true);
    }
}
