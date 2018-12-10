package View;

import Controller.SearchController;
import Model.ISQLable;
import Model.Vacation;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class MyRequests {
    public javafx.scene.control.TableView<ISQLable> reqTable;
    public javafx.scene.control.TableColumn<ISQLable,String> destination;//vecation
    public javafx.scene.control.TableColumn<ISQLable,String> startDate;//vecation
    public javafx.scene.control.TableColumn<ISQLable,String> endDate;//vecation
    public javafx.scene.control.TableColumn<ISQLable,String> sallerUserName;//vecation
    public javafx.scene.control.TableColumn<ISQLable,String> answer;//request

    public javafx.scene.control.TableView<ISQLable> reqForMeTable;
    public javafx.scene.control.TableColumn<ISQLable,String> myVecDestination;//vecation
    public javafx.scene.control.TableColumn<ISQLable,String> myVecStartDate;//vecation
    public javafx.scene.control.TableColumn<ISQLable,String> myVecEndDate;//vecation
    public javafx.scene.control.TableColumn<ISQLable,String> buyerUserName;//request
    public javafx.scene.control.TableColumn<ISQLable,String> sallerAnswer;//dont know should be changeable
    public MyRequests(){ };

    public void init() {
        destination.setCellValueFactory(new PropertyValueFactory<ISQLable,String>("_destination"));
        startDate.setCellValueFactory(new PropertyValueFactory<ISQLable,String>("__startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<ISQLable,String>("_endDate"));
        sallerUserName.setCellValueFactory(new PropertyValueFactory<ISQLable,String>("_ownerID"));
        answer.setCellValueFactory(new PropertyValueFactory<ISQLable,String>("r_answer"));

        myVecDestination.setCellValueFactory(new PropertyValueFactory<ISQLable,String>("_destination"));
        myVecStartDate.setCellValueFactory(new PropertyValueFactory<ISQLable,String>("__startDate"));
        myVecEndDate.setCellValueFactory(new PropertyValueFactory<ISQLable,String>("_endDate"));
        buyerUserName.setCellValueFactory(new PropertyValueFactory<ISQLable,String>("r_buyerID"));
        sallerAnswer.setCellValueFactory(new PropertyValueFactory<ISQLable,String>("r_answer"));
    }
 }

