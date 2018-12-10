package View;

import Controller.*;
import Model.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


import javafx.scene.control.ListView;

import java.util.Date;
import java.util.Observable;

import Model.Vacation;
import Controller.SearchController;


public class openWindowsController {
    public openWindowsController(){ };


    public javafx.scene.control.Button btn_create;
    public javafx.scene.control.TableView<Vacation> vacTable;
    public javafx.scene.control.TableColumn<Vacation,String> startDate;
    public javafx.scene.control.TableColumn<Vacation,String> endDate;
    public javafx.scene.control.TableColumn<Vacation,String> destination;
    public javafx.scene.control.TableColumn<Vacation,String> aviationCompany;
    public javafx.scene.control.TableColumn<Vacation,String> numOfTickets;
    public javafx.scene.control.TableColumn<Vacation,String> ticketType;
    public javafx.scene.control.TableColumn<Vacation,String> isBaggageIncluded;
    public javafx.scene.control.TableColumn<Vacation,String> isRoundTrip;
    public javafx.scene.control.TableColumn<Vacation,String> vacationType;
    public javafx.scene.control.TableColumn<Vacation,String> vacationStatus;
    public javafx.scene.control.TableColumn<Vacation,String> vacationSleepingArrangements;
    public javafx.scene.control.TableColumn<Vacation,String> ownerID;


    public void init() {
        startDate.setCellValueFactory(new PropertyValueFactory<Vacation,String>("__startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<Vacation,String>("__endDate"));
        destination.setCellValueFactory(new PropertyValueFactory<Vacation,String>("__destination"));
        aviationCompany.setCellValueFactory(new PropertyValueFactory<Vacation,String>("_aviationCompany"));
        numOfTickets.setCellValueFactory(new PropertyValueFactory<Vacation,String>("_numOfTickets"));
        ticketType.setCellValueFactory(new PropertyValueFactory<Vacation,String>("_ticketType"));
        isBaggageIncluded.setCellValueFactory(new PropertyValueFactory<Vacation,String>("_isBaggageIncluded"));
        isRoundTrip.setCellValueFactory(new PropertyValueFactory<Vacation,String>("_isRoundTrip"));
        vacationType.setCellValueFactory(new PropertyValueFactory<Vacation,String>("_vacationType"));
        vacationStatus.setCellValueFactory(new PropertyValueFactory<Vacation,String>("_vacationStatus"));
        vacationSleepingArrangements.setCellValueFactory(new PropertyValueFactory<Vacation,String>("_vacationSleepingArrangements"));
        ownerID.setCellValueFactory(new PropertyValueFactory<Vacation,String>("_ownerID"));

        SearchController listOfVactions = new SearchController();
        Vacation vacation = new Vacation(new Date(), new Date(), "Vienna", "el al",
                2, Vacation.TicketType.ADULT, false, true, Vacation.VacationType.URBAN,
                Vacation.VacationStatus.FOR_SALE, Vacation.VacationSleepingArrangements.NA, "Oren");
        listOfVactions.getAllAvailableVacations();
//        vacTable.setItems(data);


    }
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
            System.out.println("shit");
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
