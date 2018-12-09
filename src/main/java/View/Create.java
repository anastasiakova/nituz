package View;

import Controller.CreateController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Create {

    //public SQLModel sqlModel;
    public CreateController createController;
    @FXML
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button createButton;

    public javafx.scene.control.TextField userText;
    public javafx.scene.control.TextField passText;
    public javafx.scene.control.TextField fNameText;
    public javafx.scene.control.TextField cityText;
    public javafx.scene.control.TextField lNameText;
    public javafx.scene.control.DatePicker dateText;

    public Create() {};

//    public Create(SQLModel sqlModel) {
//        this.sqlModel = sqlModel;
//    }
    public Create(CreateController createController) {
        this.createController = createController;
    }

    public void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

//    public void setSqlModel(SQLModel sqlModel) {
//        this.sqlModel = sqlModel;
//    }

    public void setCreateController(CreateController createController) {
        this.createController = createController;
    }

    public void createButtonAction(){

        if(null == dateText.getValue()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Your'e must put a birth date! ");
            alert.show();
        }
        else {
            LocalDate localDate = dateText.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -18);
            Date d1 = calendar.getTime();
            if (date.after(d1)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Your'e too young to register! ");
                alert.show();
            } else {
//               ISQLable newUser = new User(userText.getText(), passText.getText(), date, fNameText.getText()
//                        , lNameText.getText(), cityText.getText());
//                sqlModel.insertRecordToTable(Tables.TBL_USERS.toString().toLowerCase(), newUser);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                String[] users = new String[TblFields.values().length];
//                users[0] = userText.getText();
//                String ansSelect = sqlModel.selectFromTable(Tables.TBL_USERS, users);
//                String[] arrAns = ansSelect.split(",");
                alert.setContentText("User Created:\n\n" +
                        "User Name: " + arrAns[0] + "\n"
                        + "Password: " + arrAns[1].substring(1)

                        + "\nB-day: " + arrAns[2].substring(1)
                        + "\nFirst Name: " + arrAns[3].substring(1)
                        + "\nLast Name: " + arrAns[4].substring(1)
                        + "\nCity: " + arrAns[5].substring(1, arrAns[5].length() - 2));
                alert.show();
                Stage stage = (Stage) closeButton.getScene().getWindow();
                stage.close();
            }
        }


    }



}
