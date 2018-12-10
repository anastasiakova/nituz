package View;

import Model.SQLModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        SQLModel sqlModel = SQLModel.GetInstance();
        FXMLLoader fxmlControl = new FXMLLoader();
        Parent root = fxmlControl.load(getClass().getResource(("/openWindow.fxml")).openStream());
        primaryStage.setTitle("VACATION 4 U ");
        MainView view = fxmlControl.getController();

        primaryStage.setScene(new Scene(root, 500, 275));
        primaryStage.getScene().getStylesheets().add("/openWindowCss.css");
        primaryStage.show();


    }

    public static void main (String[]args){launch(args);}
}


