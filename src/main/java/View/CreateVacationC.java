package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class CreateVacationC implements Initializable {

    //ComboBoxs:
    public ComboBox<String> ticketTypeCombo;
    public ComboBox<String> baggageCombo;
    public ComboBox<String> roundTripCombo;
    public ComboBox<String> vacType;
    public ComboBox<String> sleepCombo;
    ObservableList<String> ticketTypeList = FXCollections.observableArrayList("NA","HOTEL", "ZIMMER", "APARTMENT");
    ObservableList<String> boolList = FXCollections.observableArrayList("Yes", "No");
    ObservableList<String> boolList2 = FXCollections.observableArrayList("Yes", "No");
    ObservableList<String> vacTypeList = FXCollections.observableArrayList("URBAN", "EXOTIC", "LEISURE", "SKY");
    ObservableList<String> sleepingList = FXCollections.observableArrayList("NA", "HOTEL", "ZIMMER", "APARTMENT");

    //DatePickers and times:
    public DatePicker startDate;
    public DatePicker endDate;
    public TextField startTime;
    public TextField endTime;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ticketTypeCombo.setItems(ticketTypeList);
        baggageCombo.setItems(boolList);
        roundTripCombo.setItems(boolList2);
        vacType.setItems(vacTypeList);
        sleepCombo.setItems(sleepingList);
    }

    public void createVacation(){

    }
}
