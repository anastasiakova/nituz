package sample;

import Model.ISQLable;
import Model.SQLModel;
import Model.Tables;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class CreateController {

    public SQLModel sqlModel;
    @FXML
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button createButton;

    public javafx.scene.control.TextField userText;
    public javafx.scene.control.TextField passText;
    public javafx.scene.control.TextField fNameText;
    public javafx.scene.control.TextField cityText;
    public javafx.scene.control.TextField lNameText;
    public javafx.scene.control.DatePicker dateText;

    public CreateController() {};

    public CreateController(SQLModel sqlModel) {
        this.sqlModel = sqlModel;
    }

    public void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void setSqlModel(SQLModel sqlModel) {
        this.sqlModel = sqlModel;
    }

    public void createButtonAction(){

        LocalDate localDate = dateText.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);

        Calendar calendar  = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -18);
        Date d1 = calendar.getTime();
        if(date.after(d1)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Your'e too young to register! ");
            alert.show();
        }
        else{
            ISQLable newUser = new User(userText.getText(),passText.getText(),date,fNameText.getText()
                    ,lNameText.getText(),cityText.getText());
            sqlModel.insertRecordToTable(Tables.TBL_USERS.toString().toLowerCase(),newUser);
        }


    }



}
