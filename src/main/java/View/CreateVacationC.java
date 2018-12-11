package View;

import Model.Vacation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateVacationC implements Initializable {
    public Button addButton;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ticketTypeCombo.setItems(ticketTypeList);
        baggageCombo.setItems(boolList);
        roundTripCombo.setItems(boolList);
        vacTypeCombo.setItems(vacTypeList);
        sleepCombo.setItems(sleepingList);
    }

    public void createVacation() {
        Alert alert = new Alert(Alert.AlertType.WARNING);

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
                ticketNum.getText().equals("")) {
            alert.setContentText("Please enter all the required fields (marked in *)");
            alert.show();
        } else {
            //date & time:
            Date start = null, end = null;

            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HH:mm",Locale.ENGLISH);
            String startString = sDD.getText() + "/" + sMM.getText() + "/" + sYY.getText() + "-" +
                    sHH.getText() + ":" + sMIN.getText();
            String endString = eDD.getText() + "/" + eMM.getText() + "/" + eYY.getText() + "-" +
                    eHH.getText() + ":" + eMIN.getText();
            try {
                start = formatter.parse(startString);
                System.out.println(formatter.format(start));
                end = formatter.parse(endString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
            String ownerID = "";
            List<String> details = Arrays.asList(start.toString(), end.toString(), destination.getText(),
                    company.getText(), String.valueOf(ticketNumber), ticketType, isBaggage.toString(), isRoundTrip.toString(),
                    vacationType, sleeping);
           System.out.println(details);
            // ########## vac constructor - need to move to the big controller ##########
//            Vacation newVac = new Vacation(start, end, destination.getText(), company.getText(), ticketNumber, ticketType, isBaggage,
//                    isRoundTrip, vacationType, sleeping, ownerID);
        }
    }
}
