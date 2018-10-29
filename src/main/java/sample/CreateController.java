package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.awt.*;

public class CreateController {
    @FXML
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button createButton;

    public javafx.scene.control.TextField userText;
    public javafx.scene.control.TextField passText;
    public javafx.scene.control.TextField fNameText;
    public javafx.scene.control.TextField cityText;
    public javafx.scene.control.TextField lNameText;
    public javafx.scene.control.DatePicker dateText;

    public void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void createButtonAction(){
        System.out.println(userText.getText()+" "
                + passText.getText() +" "
                +dateText.getValue()+" "
                +fNameText.getText()+" "
                +lNameText.getText()+ " "
                +cityText.getText()+ " ");
    }



}
