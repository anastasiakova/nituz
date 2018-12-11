package View;

import Controller.LogedInController;
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
    public LogedInController logedInController;
    @FXML
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button updateButton;
    public javafx.scene.control.Label userText;

    public javafx.scene.control.TextField passText;
    public javafx.scene.control.TextField fNameText;
    public javafx.scene.control.TextField cityText;
    public javafx.scene.control.TextField lNameText;
    public javafx.scene.control.TextField bankAccountNumber;
    public javafx.scene.control.TextField creditCardNumber;
    public javafx.scene.control.TextField idNumber;
    //public javafx.scene.control.DatePicker dateText;
    public javafx.scene.control.TextField dd;
    public javafx.scene.control.TextField mm;
    public javafx.scene.control.TextField yyyy;

    void initialize() {

    };
    public UpdateForm() {};
    public UpdateForm(LogedInController logedInController) {
        this.logedInController = logedInController;
    }
    public void closeButtonAction(){
        // get a handle to the stage
        //System.out.println(shit);
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void setController(UpdateFormController updateFormController)
    {
        this.logedInController = logedInController;
    }

    public void updateFormWindow(ActionEvent actionEvent) {
    }
    public void updateButtonAction() throws InterruptedException, ParseException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (dd == null || dd.getText().equals("") ||
            mm == null || mm.getText().equals("")||
            yyyy == null || yyyy.getText().equals("")) {

            alert.setContentText("No birthday date entered. A birthday is required.");
            alert.show();
        }
           else if(!bankAccountNumber.getText().matches("\\d*")) {
                alert.setContentText("Your'e must put numbers in bank acount! ");
                alert.show();
            }
            else if(!idNumber.getText().matches("\\d*")) {
                alert.setContentText("Your'e must put a numbers in id! ");
                alert.show();
            }
         else {
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
                    alert.setContentText("Your'e too young to register! ");
                    alert.show();

                } else {
                    this.logedInController.UpdateUser(passText.getText(), date, fNameText.getText()
                            , lNameText.getText(), cityText.getText(), bankAccountNumber.getText(),
                            creditCardNumber.getText(),idNumber.getText());
                    Alert alertI = new Alert(Alert.AlertType.INFORMATION);
                    alertI.setContentText("User updated successfully.");
                    alertI.show();
                    Stage stage = (Stage) closeButton.getScene().getWindow();
                    stage.close();
                }
            }catch (ParseException e){
                alert.setContentText("Invalid date. Please insert a valid date.");
                alert.show();
            }catch (IllegalArgumentException e){
                alert.setContentText("Invalid date. Please insert a valid date.");
                alert.show();
            }

        }

    }
    }