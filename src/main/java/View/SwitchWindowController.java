package View;

import Controller.LogedInController;
import Controller.SearchController;
import Model.Vacation;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TablePosition;
import javafx.scene.control.cell.PropertyValueFactory;

public class SwitchWindowController {

    public javafx.scene.control.TableView<Vacation> myVacTable;
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
    public Label pickedVac;
    public  Button switchButton;
    public String vacID;


    private boolean userModeOn = false;
    public LogedInController logedInController;


    public void setController(LogedInController logedInController) {
        this.logedInController = logedInController;
    }

    public void SwitchVacation(String detalis,String chooseVacID){
        pickedVac.setText(detalis);
        vacID = chooseVacID;
        ObservableList<Vacation> data=null;
        data = logedInController.getAllAvailableVacations();
        myVacTable.setItems(data);
        //initButtons();
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
    }

    //the button goes here
    public void SetSwitchRequest(){
        //check if choose is vac
        //if dosnt have vacation open create vac trow the "SwitchVacation" function inside
        if(myVacTable.getItems().size()==0){
            //alert you dont have vacations
        }

        else if(!myVacTable.getSelectionModel().getSelectedCells().isEmpty()) {
            TablePosition pos = myVacTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
// Item here is the table view type:
            Vacation item = myVacTable.getItems().get(row);

            this.logedInController.CreateSwitchVacation(this.vacID, item.get_vacationID());
        }
        else {
            //alert choose vacation
        }
    }

}
