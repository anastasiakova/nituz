package View;

import Controller.LogedInController;
import Controller.SearchController;
import Model.VactaionAndRequest;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MyRequests {

    public Button reject;
    public Button approve;
    public Button markAsPaidButton;

    public Button rejectSwitch;
    public Button approveSwitch;
    public Button markAsPaidSwitch;

    LogedInController logedInController;
    public MyRequests(){}
//first tab
    public javafx.scene.control.TableView<VactaionAndRequest> reqTable;
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> destination;//vecation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> startDate;//vecation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> endDate;//vecation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> sallerUserName;//vecation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> answer;//request

    public javafx.scene.control.TableView<VactaionAndRequest> reqForMeTable;
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> myVecDestination;//vacation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> myVecStartDate;//vacation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> myVecEndDate;//vacation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> buyerUserName;//request
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> myAnswer;//request
//second tab
public javafx.scene.control.TableView<VactaionAndRequest> tradeReqTable;
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> wantedVacDestination;//vecation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> wantedVacstartDate;//vecation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> wantedVacendDate;//vecation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> wantedVacSallerUserName;//vecation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> wantedVacAnswer;//request


    public javafx.scene.control.TableView<VactaionAndRequest> tradeReqForMeTable;
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> myAskedVecDestination;//vacation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> myAskedVecStartDate;//vacation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> myAskedVecEndDate;//vacation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> initilizeUserName;//request
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> myAnswerToSwitch;//request


    public String userName="";
    public String password="";


    public void SetController(LogedInController logedInController){
        this.logedInController = logedInController;
    }

    public void init() {
        //first Tab
        destination.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("destination"));
        startDate.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("endDate"));
        sallerUserName.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("userName"));
        answer.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("answer"));

        myVecDestination.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("destination"));
        myVecStartDate.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("startDate"));
        myVecEndDate.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("endDate"));
        buyerUserName.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("userName"));
        myAnswer.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("answer"));

        ObservableList<VactaionAndRequest> myRequests = logedInController.getMyRequests(false);  // UP!!!!
        reqTable.setItems(myRequests);
        System.out.println(myRequests);
        ObservableList<VactaionAndRequest> requestsForMe = logedInController.getToAnswerRequests(false); // DOWN!!!
        reqForMeTable.setItems(requestsForMe);

        if(requestsForMe.size() == 0){
            approve.setDisable(true);
            reject.setDisable(true);
            markAsPaidButton.setDisable(true);
        }

        //second Tab
        wantedVacDestination.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("destination"));
        wantedVacstartDate.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("startDate"));
        wantedVacendDate.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("endDate"));
        wantedVacSallerUserName.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("userName"));
        wantedVacAnswer.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("answer"));

        myAskedVecDestination.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("destination"));
        myAskedVecStartDate.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("startDate"));
        myAskedVecEndDate.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("endDate"));
        initilizeUserName.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("userName"));
        myAnswerToSwitch.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("answer"));

        ObservableList<VactaionAndRequest> mySwitchRequests = logedInController.getMyRequests(true);  // UP!!!!
        tradeReqTable.setItems(mySwitchRequests);
        System.out.println(myRequests);
        ObservableList<VactaionAndRequest> switchRequestsForMe = logedInController.getToAnswerRequests(true); // DOWN!!!
        tradeReqForMeTable.setItems(switchRequestsForMe);

        if(switchRequestsForMe.size() == 0){
            approveSwitch.setDisable(true);
            rejectSwitch.setDisable(true);
            markAsPaidSwitch.setDisable(true);
        }
    }

    public void updateTextFields(String userName,String pass){
        this.userName=userName;
        this.password=pass;
    }

    public void reject(ActionEvent actionEvent) {
        if(!reqForMeTable.getSelectionModel().getSelectedCells().isEmpty()){
            TablePosition pos = reqForMeTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
// Item here is the table view type:
            VactaionAndRequest item = reqForMeTable.getItems().get(row);
            TableColumn col = pos.getTableColumn();
// this gives the value in the selected cell:
            //String data = (String) col.getCellObservableValue(item).getValue();
            logedInController.UpdateRequest("rejected",item.getReqID());
            init();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You need to peek vacation first");
        }

    }

    public void approve(ActionEvent actionEvent) {
        if(!reqForMeTable.getSelectionModel().getSelectedCells().isEmpty()) {
            TablePosition pos = reqForMeTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
// Item here is the table view type:
            VactaionAndRequest item = reqForMeTable.getItems().get(row);
            TableColumn col = pos.getTableColumn();
// this gives the value in the selected cell:
            //String data = (String) col.getCellObservableValue(item).getValue();
            logedInController.UpdateRequest("waiting_for_payment", item.getReqID());
            init();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You need to peek vacation first");
            alert.show();
        }
    }

    public void tryBuy(ActionEvent actionEvent) {
        if(!reqForMeTable.getSelectionModel().getSelectedCells().isEmpty()) {
            TablePosition pos = reqForMeTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
// Item here is the table view type:

            VactaionAndRequest item = reqForMeTable.getItems().get(row);
            if (!item.getAnswer().equals("approve")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You must approve this request first!");
                alert.show();
            } else {
                TableColumn col = pos.getTableColumn();
// this gives the value in the selected cell:
                //String data = (String) col.getCellObservableValue(item).getValue();
                logedInController.UpdateRequest("waiting_for_payment", item.getReqID());
                init();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You need to peek vacation first");
            alert.show();
        }
    }

//    public void tryBuy(ActionEvent actionEvent) {
//        if(!reqTable.getSelectionModel().getSelectedCells().isEmpty()){
//            TablePosition pos = reqTable.getSelectionModel().getSelectedCells().get(0);
//            int row = pos.getRow();
//// Item here is the table view type:
//            VactaionAndRequest item = reqTable.getItems().get(row);
//            TableColumn col = pos.getTableColumn();
//// this gives the value in the selected cell:
//
//            if(item.getAnswer().equals("confirmed")){
//                try {
//                    Stage stage = new Stage();
//                    stage.setTitle("payment vacation");
//                    FXMLLoader fxmlLoader = new FXMLLoader();
//                    Parent root = fxmlLoader.load(getClass().getResource("/Payment.fxml").openStream());
//                    PaymentController creatView = fxmlLoader.getController();
//                    Scene scene = new Scene(root, 340, 350);
//                    stage.setScene(scene);
//                    //stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
//                    stage.showAndWait();
//                    logedInController.CreatePaymentAndUpdateVacation(item.getReqID());
//                    init();
//                } catch (Exception e) {
//
//                }
//            }
//            else{
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setContentText("Can't buy vacation if request was rejected");
//                alert.show();
//            }
//        }
//        else {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setContentText("You need to peek vacation first");
//            alert.show();
//        }
//    }


    public void goBack(ActionEvent actionEvent) throws IOException {
        try {
            Stage stage = new Stage();
            stage.setTitle("update User");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("/openWindow.fxml").openStream());
            openWindowsController creatView = fxmlLoader.getController();
            creatView.setController(this.logedInController);
            Scene scene = new Scene(root);
            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.getScene().getStylesheets().add("/openWindowCss.css");
            //System.out.println(userName+"---"+password);
            creatView.returnLogIn(userName,password);

//            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            window.show();
        } catch (Exception e) {

        }

    }
}

