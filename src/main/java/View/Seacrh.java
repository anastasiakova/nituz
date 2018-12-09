package View;

import Controller.SearchController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Seacrh {
    @FXML
    //private AnchorPane content;
    //public static String ans;
    //public SQLModel sqlModel;
    public SearchController searchController;
    @FXML
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button updateButton;
    public javafx.scene.control.TextField searchText;
    //public javafx.scene.control.TextField userText;

    public Seacrh() {};


//    public Seacrh(SQLModel sqlModel) {
//        this.sqlModel = sqlModel;
//    }
    public Seacrh(SearchController searchController) {

        this.searchController = searchController;
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
    public void setController(SearchController searchController)
    {
        this.searchController = searchController;
    }

    public void updateFormWindow(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String[]users = new String[TblFields.values().length];
        users[0] = searchText.getText();
        String ansSelect = sqlModel.selectFromTable(Tables.TBL_USERS,users);

        if(ansSelect == "") {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("User does not exist in the system! ");
            alert1.show();
        }
        else {
            String[] arrAns = ansSelect.split(",");
            alert.setContentText("User detalis:\n\n" +
                    "User Name: " + arrAns[0] + "\n"
                    + "Password: " + arrAns[1].substring(1)

                    + "\nB-day: " + arrAns[2].substring(1)
                    + "\nFirst Name: " + arrAns[3].substring(1)
                    + "\nLast Name: " + arrAns[4].substring(1)
                    + "\nCity: " + arrAns[5].substring(1, arrAns[5].length() - 2));

            alert.show();
        }


    }




}
