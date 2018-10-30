package sample;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class UpdateFormController{


    public SQLModel sqlModel;
    @FXML
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button updateButton;
    public javafx.scene.control.Label userText;

    public javafx.scene.control.TextField passText;
    public javafx.scene.control.TextField fNameText;
    public javafx.scene.control.TextField cityText;
    public javafx.scene.control.TextField lNameText;
    //public javafx.scene.control.DatePicker dateText;
    public javafx.scene.control.TextField dd;
    public javafx.scene.control.TextField mm;
    public javafx.scene.control.TextField yyyy;

    void initialize() {

    };



    //public String shit=ans;
    public UpdateFormController() {};
    public UpdateFormController(SQLModel sqlModel) {
        this.sqlModel = sqlModel;
    }

    public void closeButtonAction(){
        // get a handle to the stage
        //System.out.println(shit);
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void setSqlModel(SQLModel sqlModel) {
        this.sqlModel = sqlModel;
    }

    public void updateFormWindow(ActionEvent actionEvent) {
    }
    public void updateButtonAction() throws InterruptedException, ParseException {

        if (dd == null || mm == null || yyyy == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Your'e must put a birth date! ");
            alert.show();
        } else {
            //LocalDate localDate = dateText.getValue();
            //Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            //Date date = Date.from(instant);
            DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateAsString = dd.getText() + "/" + mm.getText() + "/" + yyyy.getText();
            Date date = sourceFormat.parse(dateAsString);
            System.out.println(date);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -18);
            Date d1 = calendar.getTime();
            if (date.after(d1)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Your'e too young to register! ");
                alert.show();

            } else {
                ISQLable newUser = new User(userText.getText(), passText.getText(), date, fNameText.getText()
                        , lNameText.getText(), cityText.getText());
                sqlModel.updateRecord(newUser);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("User update successfully");
                alert.show();
                Stage stage = (Stage) closeButton.getScene().getWindow();
                stage.close();
            }

        }

    }
    }