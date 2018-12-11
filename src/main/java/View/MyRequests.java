package View;

import Controller.LogedInController;
import Controller.SearchController;
import Model.VactaionAndRequest;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.cell.PropertyValueFactory;

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

    public void SetController(LogedInController logedInController){
        this.logedInController = logedInController;
    }

    public void init() {
        destination.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("_destination"));
        startDate.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("__startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("_endDate"));
        sallerUserName.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("_ownerID"));
        answer.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("r_answer"));

        myVecDestination.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("_destination"));
        myVecStartDate.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("__startDate"));
        myVecEndDate.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("_endDate"));
        buyerUserName.setCellValueFactory(new PropertyValueFactory<VactaionAndRequest,String>("r_buyerID"));

        ObservableList<VactaionAndRequest> myRequests = logedInController.getMyRequests();
        reqTable.setItems(myRequests);
        ObservableList<VactaionAndRequest> requestsForMe = logedInController.getToAnswerRequests();
        reqForMeTable.setItems(requestsForMe);
    }

    public void reject(ActionEvent actionEvent) {

    }

    public void approve(ActionEvent actionEvent) {
    }

    public void tryBuy(ActionEvent actionEvent) {
        TablePosition pos = reqTable.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
// Item here is the table view type:
        VactaionAndRequest item = reqTable.getItems().get(row);
        TableColumn col = pos.getTableColumn();
// this gives the value in the selected cell:
        String data = (String) col.getCellObservableValue(item).getValue();

        if(data == "confirmed"){
            //show orens buy form
        }
        else{

        }
    }
}

