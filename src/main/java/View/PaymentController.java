package View;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    public void payButton() {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        if ((mm == null || mm.getText().equals("") ||
                yy == null || yy.getText().equals("") ||
                cardNum.getText().equals("") || cvv.getText().equals("")|| cvv == null ||
                ID.getText().equals(""))||(userPP.getText().equals("")||pswPP.getText().equals(""))){
            alert.setContentText("Please enter all the required fields (marked in *)");
            alert.show();
        }
    }
}
