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
    public Button createUserButton;
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
    public LogedInController logedInController;
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
    public boolean userModeOn=false;

    public void initButtons(){
        this.logOutButton.setVisible(false);
        this.welcomeLabel.setVisible(false);
        if(vacTable.getItems().size() == 0)
            this.buyThisVacaionButton.setDisable(true);
        this.addVactionButton.setVisible(false);
        this.allMyRequestButton.setVisible(false);
        this.updateDetalisButton.setVisible(false);
    }
    public void initialize() {
        initButtons();
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
        if(userModeOn)
            data = logedInController.getAllAvailableVacations();
        else
            data = listOfVactions.getAllAvailableVacations();
        vacTable.setItems(data);

    }

    public void logINButtonAction(ActionEvent event) throws IOException {
        //validate user name & password
        boolean loginSuccessful = false;
        if (userText.getText() != "" && passText.getText() != "") {
            System.out.println(userText.getText());
            //controller search
            this.logedInController = new LogedInController();
//            loginSuccessful = searchController.isLoginValid(userText.getText(), passText.getText());
            loginSuccessful = logedInController.tryLogIn(userText.getText(),passText.getText());
            if (loginSuccessful) {
                userModeOn=true;
                initialize();
                loginButtonsMaker(event);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("You Suck!");
                alert.show();
            }

        }
    }

    public void loginButtonsMaker(ActionEvent event) {
        this.createUserButton.setVisible(false);
        this.userText.setVisible(false);
        this.loginButton.setVisible(false);
        this.passText.setVisible(false);
        this.useLabel.setVisible(false);
        this.passLabel.setVisible(false);
        this.logOutButton.setVisible(true);
        this.welcomeLabel.setText("Welcome "+this.userText.getText()+"!");
        this.welcomeLabel.setVisible(true);
        if(vacTable.getItems().size() > 0)
            this.buyThisVacaionButton.setDisable(false);
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
        primaryStage.setScene(new Scene(root, 500, 520));
        primaryStage.getScene().getStylesheets().add("/subWindowsCss.css");
        primaryStage.showAndWait();
        initialize();
        loginButtonsMaker(event);

    }
    public static void bla(){
        System.out.println("sdfsdf");
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
        if(vacTable.getItems().size() == 0)
            this.buyThisVacaionButton.setDisable(true);
        this.addVactionButton.setVisible(false);
        this.allMyRequestButton.setVisible(false);
        this.updateDetalisButton.setVisible(false);
        this.t1.setVisible(true);
        this.t2.setVisible(true);
        this.t3.setVisible(true);
        this.t4.setVisible(true);
        this.logedInController.LogOut();
        this.userModeOn=false;
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
            Scene scene = new Scene(root, 270, 420);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
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
            stage.setTitle("Update User");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("/UpdateForm.fxml").openStream());
            UpdateForm creatView = fxmlLoader.getController();
            creatView.setController(new UpdateFormController());
            Scene scene = new Scene(root, 400, 550);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {

        }
    }
}
