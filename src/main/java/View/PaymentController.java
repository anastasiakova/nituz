package View;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PaymentController {
    public TextField cardNum;
    public TextField mm;
    public TextField yy;
    public TextField cvv;
    public TextField ID;
    public TextField userPP;
    public TextField pswPP;
    public Button payPP;
    public Button payCC;


    public PaymentController() {
    }

    public void payButtonCC() {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        if ((mm == null || mm.getText().equals("") ||
                yy == null || yy.getText().equals("") ||
                cardNum.getText().equals("") || cvv.getText().equals("") || cvv == null ||
                ID.getText().equals(""))) {
            alert.setContentText("Please enter all the required fields");
            alert.show();
        } else if ((!cardNum.getText().matches("\\d*") || !mm.getText().matches("\\d\\d") ||
                !yy.getText().matches("\\d\\d") || !cvv.getText().matches("\\d\\d\\d") ||
                !ID.getText().matches("\\d{9}") ||
                (Integer.parseInt(mm.getText()) <= 12 && Integer.parseInt(mm.getText()) > 0))) {
            alert.setContentText("Please enter valid numbers in the required fields");
            alert.show();
        }
        else{
            Stage stage = (Stage) payCC.getScene().getWindow();
            stage.close();
        }
    }
    public void payButtonPP() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (userPP.getText().equals("") || pswPP.getText().equals("")){
            alert.setContentText("Please enter all the required fields");
            alert.show();
        }
        else{
            Stage stage = (Stage) payPP.getScene().getWindow();
            stage.close();
        }
    }
}
