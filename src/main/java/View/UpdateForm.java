package View;

import Controller.UpdateFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateForm {


   // public SQLModel sqlModel;
    public UpdateFormController updateFormController;
    @FXML
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button updateButton;
    public javafx.scene.control.Label userText;

    public javafx.scene.control.TextField passText;
    public javafx.scene.control.TextField fNameText;
    public javafx.scene.control.TextField cityText;
    public javafx.scene.control.TextField lNameText;
    public javafx.scene.control.TextField bankAcountNumber;
    public javafx.scene.control.TextField creditCardNumber;
    public javafx.scene.control.TextField idNumber;
    //public javafx.scene.control.DatePicker dateText;
    public javafx.scene.control.TextField dd;
    public javafx.scene.control.TextField mm;
    public javafx.scene.control.TextField yyyy;

    void initialize() {

    };

    //public String shit=ans;
    public UpdateForm() {};
//    public UpdateForm(SQLModel sqlModel) {
//        this.sqlModel = sqlModel;
//    }
    public UpdateForm(UpdateFormController updateFormController) {
        this.updateFormController = updateFormController;
    }

    public void closeButtonAction(){
        // get a handle to the stage
        //System.out.println(shit);
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

//    public void setSqlModel(SQLModel sqlModel) {
//        this.sqlModel = sqlModel;
//    }
    public void setController(UpdateFormController updateFormController)
    {
        this.updateFormController = updateFormController;
    }

    public void updateFormWindow(ActionEvent actionEvent) {
    }
    public void updateButtonAction() throws InterruptedException, ParseException {

        if (dd == null || dd.getText().equals("") ||
            mm == null || mm.getText().equals("")||
            yyyy == null || yyyy.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("No birthday date entered. A birthday is required.");
            alert.show();
        } else {
            //LocalDate localDate = dateText.getValue();
            //Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            //Date date = Date.from(instant);
            DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
            sourceFormat.setLenient(false);
            String dateAsString = dd.getText() + "/" + mm.getText() + "/" + yyyy.getText();

            try {
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
                    this.updateFormController.UpdateUser(userText.getText(), passText.getText(), date, fNameText.getText()
                            , lNameText.getText(), cityText.getText(), bankAcountNumber.getText(),
                            creditCardNumber.getText(),idNumber.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("User updated successfully.");
                    alert.show();
                    Stage stage = (Stage) closeButton.getScene().getWindow();
                    stage.close();
                }
            }catch (ParseException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Invalid date. Please insert a valid date.");
                alert.show();
            }catch (IllegalArgumentException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Invalid date. Please insert a valid date.");
                alert.show();
            }

        }

    }
    }