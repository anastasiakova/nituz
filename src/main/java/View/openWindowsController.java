package View;

import Controller.*;
import Model.User;
import Model.Vacation;
import Model.VactaionAndRequest;
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
    public Button createUserButton;
    @FXML
    public Button loginButton;
    public Button logOutButton;
    public Button allMyRequestButton;
    public Button updateDetalisButton;
    public Button buyThisVacaionButton;
    public Button addVactionButton;
    public Button switchThisVacaionButton;

    public TextField userText;
    public TextField passText;
    public Label t1;
    public Label t2;
    public Label t3;
    public Label t4;

    public Label passLabel;
    public Label useLabel;
    public Label welcomeLabel;
    public Label vaca4u;
    public LogedInController logedInController;

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
    public boolean userModeOn = false;
    public String userName="";
    public String password="";
    public String VactionToSwitchDetalis="";
    public User activeUser= null;
    public openWindowsController() {
    }

    public openWindowsController(LogedInController logedInController) { this.logedInController = logedInController;}

    public void setController(LogedInController logedInController) {
        this.logedInController = logedInController;
    }


    public void initButtons(){
        this.logOutButton.setVisible(false);
        this.welcomeLabel.setVisible(false);
        if(vacTable.getItems().size() == 0) {
            this.buyThisVacaionButton.setDisable(true);
            this.switchThisVacaionButton.setDisable(true);
        }
        this.addVactionButton.setVisible(false);
        this.allMyRequestButton.setVisible(false);
        this.updateDetalisButton.setVisible(false);
    }
    public void initialize() {
        initButtons();
        vaca4u.getStyleClass().add("titleCss");
        startDate.setCellValueFactory(new PropertyValueFactory<Vacation, String>("__startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_endDate"));
        destination.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_destination"));
        aviationCompany.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_aviationCompany"));
        numOfTickets.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_numOfTickets"));
        ticketType.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_ticketType"));
        isBaggageIncluded.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_isBaggageIncluded"));
        isRoundTrip.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_isRoundTrip"));
        vacationType.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_vacationType"));
        //vacationStatus.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_vacationStatus"));
        vacationSleepingArrangements.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_vacationSleepingArrangements"));
        //ownerID.setCellValueFactory(new PropertyValueFactory<Vacation, String>("_ownerID"));
        SearchController listOfVactions = new SearchController();
        ObservableList<Vacation> data=null;
//        if(userModeOn)
//            data = logedInController.getAllAvailableVacations();
//        else
            data = listOfVactions.getAllAvailableVacations();
        vacTable.setItems(data);

    }

    public void logINButtonAction(ActionEvent event) throws IOException {
        userName=userText.getText();
        password=passText.getText();
        //validate user name & password
        boolean loginSuccessful = false;
        if (userName != "" && password != "") {
            //System.out.println(userText.getText());
            //controller search
            this.logedInController = new LogedInController();
//            loginSuccessful = searchController.isLoginValid(userText.getText(), passText.getText());
            loginSuccessful = logedInController.tryLogIn(userName,password);
            if (loginSuccessful) {
                userModeOn=true;
                initialize();
                loginButtonsMaker();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("You Suck!");
                alert.show();
            }

        }
    }


    public void returnLogIn(String userName,String password) throws IOException {
        this.userName=userName;
        this.password=password;
        //validate user name & password
        boolean loginSuccessful = false;
        if (userName != "" && password != "") {
            //System.out.println(userText.getText());
            //controller search
            this.logedInController = new LogedInController();
//            loginSuccessful = searchController.isLoginValid(userText.getText(), passText.getText());
            loginSuccessful = logedInController.tryLogIn(userName,password);
            if (loginSuccessful) {
                userModeOn=true;
                initialize();
                loginButtonsMaker();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("You Suck!");
                alert.show();
            }

        }
    }
    public void loginButtonsMaker() {
        this.createUserButton.setVisible(false);
        this.userText.setVisible(false);
        this.loginButton.setVisible(false);
        this.passText.setVisible(false);
        this.useLabel.setVisible(false);
        this.passLabel.setVisible(false);
        this.logOutButton.setVisible(true);
        this.welcomeLabel.setText("Welcome "+userName+"!");
        this.welcomeLabel.setVisible(true);
        if(vacTable.getItems().size() > 0) {
            this.buyThisVacaionButton.setDisable(false);
            this.switchThisVacaionButton.setDisable(false);
        }
        this.addVactionButton.setVisible(true);
        this.allMyRequestButton.setVisible(true);
        this.updateDetalisButton.setVisible(true);
        this.t1.setVisible(false);
        this.t2.setVisible(false);
        this.t3.setVisible(false);
        this.t4.setVisible(false);

    }

    public void openCreateVactionWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlControl = new FXMLLoader();
        Parent root = fxmlControl.load(getClass().getResource("/CreateVacation.fxml").openStream());
        Stage primaryStage = new Stage();
        CreateVacationC createVacationC = fxmlControl.getController();
        createVacationC.setController(this.logedInController);
        primaryStage.setTitle("Create New Vaction");
        primaryStage.setScene(new Scene(root, 520, 500));
        primaryStage.getScene().getStylesheets().add("/subWindowsCss.css");
        primaryStage.showAndWait();
        initialize();
        loginButtonsMaker();

    }

    public void logOut() {
        this.searchController = new SearchController();
        this.createUserButton.setVisible(true);
        this.userText.setVisible(true);
        this.userText.setText("");
        this.passText.setText("");
        this.loginButton.setVisible(true);
        this.passText.setVisible(true);
        this.useLabel.setVisible(true);
        this.passLabel.setVisible(true);
        this.logOutButton.setVisible(false);
        this.welcomeLabel.setVisible(false);
        //if(vacTable.getItems().size() == 0) {
            this.buyThisVacaionButton.setDisable(true);
            this.switchThisVacaionButton.setDisable(true);
        //}
        this.addVactionButton.setVisible(false);
        this.allMyRequestButton.setVisible(false);
        this.updateDetalisButton.setVisible(false);
        this.t1.setVisible(true);
        this.t2.setVisible(true);
        this.t3.setVisible(true);
        this.t4.setVisible(true);
        this.logedInController.LogOut();
        this.userModeOn=false;
//        loginButtonsMaker();
        initialize();
    }

    public void unVisibleButtons(ActionEvent event){
        if(userModeOn){
            createUserButton.setVisible(false);
        }
    }

    public void createUserWindow(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Create User");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("/Create.fxml").openStream());
            Create creatView = fxmlLoader.getController();
            creatView.setCreateController(new CreateController());
            Scene scene = new Scene(root, 350, 450);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.showAndWait();
            initialize();

        } catch (Exception e) {
            System.out.println("cant do it!! ");
        }
    }

    public void myRequestWindow(ActionEvent actionEvent) throws IOException {
            Stage stage = new Stage();
            stage.setTitle("My requestes:");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("/MyRequests.fxml").openStream());
            MyRequests creatView = fxmlLoader.getController();
            creatView.SetController(this.logedInController);
            creatView.updateTextFields(userName,password);
            Scene scene = new Scene(root);
            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            creatView.init();
//            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            window.show();
    }

    public void updateWindow(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("update User");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("/UpdateForm.fxml").openStream());
            UpdateForm creatView = fxmlLoader.getController();
            creatView.setController(this.logedInController);
            Scene scene = new Scene(root, 380, 550);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {

        }
    }

    public void setRequest(ActionEvent actionEvent) {
        if(!vacTable.getSelectionModel().getSelectedCells().isEmpty()){
            TablePosition pos = vacTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
// Item here is the table view type:
            Vacation item = vacTable.getItems().get(row);
// this gives the value in the selected cell:
            if(item.get_ownerID().equals(userName)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You can't Buy your own vacations! pick another");
                alert.show();
            }
            else {
                logedInController.CreateRequestAndUpdateVacation(item.get_ownerID(), item.get_vacationID());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Request sended");
                alert.show();
                initialize();
                loginButtonsMaker();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You need to peek vacation first");
            alert.show();
        }
    }

    public void switchVac(ActionEvent actionEvent){
        try {
            if(!vacTable.getSelectionModel().getSelectedCells().isEmpty()) {
                TablePosition pos = vacTable.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
// Item here is the table view type:
                Vacation item = vacTable.getItems().get(row);
                if(item.get_ownerID().equals(userName)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("You can't Buy your own vacations! pick another");
                    alert.show();
                }
                else {
                    VactionToSwitchDetalis = "Owner ID: " + item.get_ownerID() + " | " + "Destination: " + item.get_destination() + " | " + "Start: " + item.get__startDate() + " | " +
                            "End: " + item.get_endDate();
                    Stage stage = new Stage();
                    stage.setTitle("Switch User");
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    Parent root = fxmlLoader.load(getClass().getResource("/SwitchWindow.fxml").openStream());
                    SwitchWindowController creatView = fxmlLoader.getController();
                    creatView.setController(this.logedInController);
                    creatView.SwitchVacation(VactionToSwitchDetalis,item.get_vacationID());
                    Scene scene = new Scene(root, 820, 450);
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
                    stage.show();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("You need to peek vacation first");
                alert.show();
            }
        } catch (Exception e) {
            System.out.print("BIG ERROR");

        }
    }
}
