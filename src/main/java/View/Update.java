package View;

import Controller.UpdateController;
import Controller.UpdateFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Update {

    @FXML
    //private AnchorPane content;
    //public static String ans;
    // public SQLModel sqlModel;
    public UpdateController updateController;
    @FXML
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button updateButton;
    public javafx.scene.control.TextField updateText;
    public javafx.scene.control.TextField dd;
    public javafx.scene.control.TextField mm;
    public javafx.scene.control.TextField yyyy;
    //public javafx.scene.control.TextField userText;

    public Update() { };

    //    public Update(SQLModel sqlModel) {
//        this.sqlModel = sqlModel;
//    }
    public Update(UpdateController updateController) {
        this.updateController = updateController;
    }

    public void closeButtonAction() {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    //    public void setSqlModel(SQLModel sqlModel) {
//        this.sqlModel = sqlModel;
//    }
    public void setController(UpdateController updateController) {

        this.updateController = updateController;
    }

    public void updateFormWindow(ActionEvent actionEvent) {
//        String[]users = new String[TblFields.values().length];
//        users[0] = searchText.getText();
//        String ansSelect = sqlModel.selectFromTable(Tables.TBL_USERS,users);
//        String[]arrAns = ansSelect.split(",");
//        if(ansSelect == "") {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("User does not exist in the system! ");
//            alert.show();
//        }

        String massege = updateController.UpdateUser(updateText.getText());
        String[]arrAns = massege.split(",");

        try {
            Stage stage = new Stage();
            stage.setTitle("Update User");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("/UpdateForm.fxml").openStream());
            UpdateForm ViewForm = fxmlLoader.getController();
            ViewForm.setController(new UpdateFormController());
            Scene scene = new Scene(root, 400, 550);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            ViewForm.userText.setText(updateText.getText());
            ViewForm.passText.setText(arrAns[1].substring(1));
            ViewForm.fNameText.setText(arrAns[3].substring(1));
            ViewForm.lNameText.setText(arrAns[4].substring(1));
            String[] date = fromStringToDate(arrAns[2]).split("/");
            ViewForm.dd.setText(date[0]);
            ViewForm.mm.setText(date[1]);
            ViewForm.yyyy.setText(date[2]);
            ViewForm.cityText.setText(arrAns[5].substring(1, arrAns[5].length() - 2));
            stage.show();
            //ViewForm.dateText.setValue(LocalDate.of(Integer.parseInt(date[3]),Integer.parseInt(date[2]),Integer.parseInt(date[1])));
            //System.out.println(ViewForm.dateText.getValue());
            // ViewForm.dateText
        } catch (Exception e) {
        }
    }

    private String fromStringToDate(String arrAn) throws ParseException {
        String[] goodFormatDate = arrAn.split(" ");
        Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(goodFormatDate[2]);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        return (goodFormatDate[3] + "/" + month + "/" + goodFormatDate[goodFormatDate.length - 1]);
    }
}
