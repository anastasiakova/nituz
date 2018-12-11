package View;

import Controller.SearchController;
import Model.VactaionAndRequest;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class MyRequests {
    SearchController searchController;
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

    public MyRequests(SearchController searchController){
        this.searchController = searchController;
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

//        ObservableList<VactaionAndRequest> myRequests = searchController.getMyRequests();
//        reqTable.setItems(myRequests);
//        ObservableList<VactaionAndRequest> requestsForMe = searchController.getToAnswerRequests();
//        reqForMeTable.setItems(requestsForMe);
    }
 }

