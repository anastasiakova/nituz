package sample;

import Model.SQLModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        SQLModel sqlModel = SQLModel.GetInstance();
        FXMLLoader fxmlControl = new FXMLLoader();
        Parent root = fxmlControl.load(getClass().getResource(("/openWindow.fxml")).openStream());
        primaryStage.setTitle("VACATION 4 U ");
        MainController view = fxmlControl.getController();
        view.setSqlModel(sqlModel);


        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


    }

    public static void main (String[]args){launch(args);}
}


