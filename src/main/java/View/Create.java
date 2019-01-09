package View;

import Controller.CreateController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public javafx.scene.control.TextField dd;
    public javafx.scene.control.TextField mm;
    public javafx.scene.control.TextField yy;
    public TextField idNumber;
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public Create() {}

    public Create(CreateController createController) {
        this.createController = createController;
    }

    public void closeButtonAction() {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void setCreateController(CreateController createController) {
        this.createController = createController;
    }

    public void createButtonAction() throws ParseException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        String bDay = dd.getText() + "/" + mm.getText() + "/" + yy.getText();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -18);
        Date d1 = calendar.getTime();
        Date date = null;
        //System.out.println(Calendar.getInstance().get(Calendar.YEAR));

        if (dd == null || dd.getText().equals("") ||
                mm == null || mm.getText().equals("") ||
                yy == null || yy.getText().equals("") || userText.getText().equals("") ||
                passText.getText().equals("") || fNameText.getText().equals("") || lNameText.getText().equals("") ||
                cityText.getText().equals("") || idNumber.getText().equals("")) {
            alert.setContentText("Please enter all the required fields");
            alert.show();
        } else if (!idNumber.getText().matches("\\d*")) {
            alert.setContentText("You must put a numbers in ID! ");
            alert.show();
        }
        else if(Integer.parseInt(yy.getText()) > Calendar.getInstance().get(Calendar.YEAR)){
            alert.setContentText("The year you entered is wrong! ");
            alert.show();
        }else if (formatter.parse(bDay).after(d1)) {
            alert.setContentText("You are too young to register! ");
            alert.show();
        } else {
            date = formatter.parse(bDay);
            this.createController.CreateUser(userText.getText(), passText.getText(), date, fNameText.getText()
                    , lNameText.getText(), cityText.getText(), idNumber.getText());//TODO should be fields for that
            Alert alertI = new Alert(Alert.AlertType.INFORMATION);
            alertI.setContentText("User Created:\n\n" + createController.getUserCreatedMassage(userText.getText(),
                    true));
            alertI.show();
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
    }
}






