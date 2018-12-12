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
import javafx.stage.Stage;

import java.io.IOException;

public class MyRequests {

    public Button reject;
    public Button approve;
    public Button buy;
    LogedInController logedInController;
    public MyRequests(){};

    public javafx.scene.control.TableView<VactaionAndRequest> reqTable;
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> destination;//vecation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> startDate;//vecation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> endDate;//vecation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> sallerUserName;//vecation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> answer;//request

    public javafx.scene.control.TableView<VactaionAndRequest> reqForMeTable;
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> myVecDestination;//vecation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> myVecStartDate;//vecation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> myVecEndDate;//vecation
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> buyerUserName;//request
    public javafx.scene.control.TableColumn<VactaionAndRequest,String> myAnswer;//request

    public void SetController(LogedInController logedInController){
        this.logedInController = logedInController;
    }

    public void init() {
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

        ObservableList<VactaionAndRequest> myRequests = logedInController.getMyRequests();
        reqTable.setItems(myRequests);
        ObservableList<VactaionAndRequest> requestsForMe = logedInController.getToAnswerRequests();
        reqForMeTable.setItems(requestsForMe);
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
            logedInController.UpdateRequest("confirmed", item.getReqID());
            init();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You need to peek vacation first");
            alert.show();
        }
    }

    public void tryBuy(ActionEvent actionEvent) {
        if(!reqTable.getSelectionModel().getSelectedCells().isEmpty()){
            TablePosition pos = reqTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
// Item here is the table view type:
            VactaionAndRequest item = reqTable.getItems().get(row);
            TableColumn col = pos.getTableColumn();
// this gives the value in the selected cell:

            if(item.getAnswer().equals("confirmed")){
                //show orens buy form
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Can't buy vacation if request was rejected");
                alert.show();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You need to peek vacation first");
            alert.show();
        }
    }


    public void goBack(ActionEvent actionEvent) throws IOException {
//        Stage stage = new Stage();
//        stage.setTitle("VACTION 4 U");
//        Parent root = FXMLLoader.load(getClass().getResource("/openWindowController.fxml"));
//        Scene scene = new Scene(root);
//        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
//        window.setScene(scene);
////            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
//        window.show();
    }
}

