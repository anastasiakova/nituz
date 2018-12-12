package View;

import Controller.DeleteController;
import Controller.LogedInController;
import Model.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateVacationC implements Initializable {
    public Button addButton;
    LogedInController logedInController;
    //ComboBoxs:
    public ComboBox<String> ticketTypeCombo;
    public ComboBox<String> baggageCombo;
    public ComboBox<String> roundTripCombo;
    public ComboBox<String> vacTypeCombo;
    public ComboBox<String> sleepCombo;
    ObservableList<String> ticketTypeList = FXCollections.observableArrayList("ADULT", "CHILD", "INFANT");
    ObservableList<String> boolList = FXCollections.observableArrayList("Yes", "No");
    ObservableList<String> vacTypeList = FXCollections.observableArrayList("URBAN", "EXOTIC", "LEISURE", "SKY");
    ObservableList<String> sleepingList = FXCollections.observableArrayList("NA", "HOTEL", "ZIMMER", "APARTMENT");

    //Date and time:
    public TextField sDD;
    public TextField sMM;
    public TextField sYY;
    public TextField sHH;
    public TextField sMIN;
    public TextField eDD;
    public TextField eMM;
    public TextField eYY;
    public TextField eHH;
    public TextField eMIN;

    //other fields:
    public TextField destination;
    public TextField company;
    public TextField ticketNum;
    Date start = null, end = null;
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HH:mm");

    public CreateVacationC() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ticketTypeCombo.setItems(ticketTypeList);
        baggageCombo.setItems(boolList);
        roundTripCombo.setItems(boolList);
        vacTypeCombo.setItems(vacTypeList);
        sleepCombo.setItems(sleepingList);
    }

    public void createVacation() throws ParseException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        String startString = sDD.getText() + "/" + sMM.getText() + "/" + sYY.getText() + "-" +
                sHH.getText() + ":" + sMIN.getText();
        String endString = eDD.getText() + "/" + eMM.getText() + "/" + eYY.getText() + "-" +
                eHH.getText() + ":" + eMIN.getText();
        if (sDD == null || sDD.getText().equals("") ||
                sMM == null || sMM.getText().equals("") ||
                sYY == null || sYY.getText().equals("") ||
                sHH == null || sHH.getText().equals("") ||
                sMIN == null || sMIN.getText().equals("") ||
                eDD == null || eDD.getText().equals("") ||
                eMM == null || eMM.getText().equals("") ||
                eYY == null || eYY.getText().equals("") ||
                eHH == null || eHH.getText().equals("") ||
                eMIN == null || eMIN.getText().equals("") ||
                destination.getText().equals("") ||
                ticketNum.getText().equals("") || ticketTypeCombo.getValue() == null ||
                roundTripCombo.getValue() == null || baggageCombo.getValue() == null ||
                sleepCombo.getValue() == null) {
            alert.setContentText("Please enter all the required fields (marked in *)");
            alert.show();
        } else if (formatter.parse(startString).after(formatter.parse(endString))) {
            //date & time:
            alert.setContentText("Please enter valid dates");
            alert.show();
        } else if (!ticketNum.getText().matches("\\d*") ||
                (Integer.parseInt(sDD.getText()) > 31 && Integer.parseInt(sDD.getText()) <= 0) ||
                (Integer.parseInt(eDD.getText()) > 31 && Integer.parseInt(eDD.getText()) <= 0) ||
                (Integer.parseInt(sMM.getText()) > 12 && Integer.parseInt(sMM.getText()) <= 0) ||
                (Integer.parseInt(eMM.getText()) > 12 && Integer.parseInt(eMM.getText()) <= 0)) {
            alert.setContentText("Please enter only number in the number of tickets");
            alert.show();
        } else if ((Integer.parseInt(sHH.getText()) < 0 && Integer.parseInt(sHH.getText()) > 23) ||
                (Integer.parseInt(sMIN.getText()) < 0 && Integer.parseInt(sMIN.getText()) >= 60) ||
                (Integer.parseInt(eHH.getText()) < 0 && Integer.parseInt(eHH.getText()) > 23) ||
                (Integer.parseInt(eMIN.getText()) < 0 && Integer.parseInt(eMIN.getText()) >= 60)) {
            alert.setContentText("Please enter valid times");
            alert.show();
        } else {
            //string fields:
            int ticketNumber = 0;
            try {
                ticketNumber = Integer.parseInt(ticketNum.getText());
            } catch (Exception e) {
                alert.setContentText("Please enter only number in the number of tickets");
                alert.show();
            }
            String ticketType = ticketTypeCombo.getValue();
            Boolean isBaggage;
            if (baggageCombo.getValue() == "Yes")
                isBaggage = true;
            else isBaggage = false;

            Boolean isRoundTrip;
            if (roundTripCombo.getValue() == "Yes")
                isRoundTrip = true;
            else isRoundTrip = false;
            String vacationType = vacTypeCombo.getValue();
            String sleeping = sleepCombo.getValue();
            logedInController.CreateVacation(startString, endString, destination.getText(), company.getText(), ticketNumber,
                    ticketType, isBaggage, isRoundTrip, vacationType, sleeping);
            // ########## vac constructor - need to move to the big controller ##########
//            Vacation newVac = new Vacation(start, end, destination.getText(), company.getText(), ticketNumber, ticketType, isBaggage,
//                    isRoundTrip, vacationType, sleeping, ownerID);

            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        }
    }

    public void setController(LogedInController controller) {
        this.logedInController = controller;
    }
}
