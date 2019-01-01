package Controller;

import Model.*;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class SearchController {

    SQLModel sqlModel;

    public SearchController() {
        this.sqlModel = SQLModel.GetInstance();
    }

    public String getMassageAfterChange(String userText, boolean withPassword) {
        String[] users = new String[TblFields.enumDict.get("userFields").size()];
        users[0] = userText;
        String ansSelect = sqlModel.selectFromTable(Tables.TBL_USERS, users);

        if (ansSelect == "") {
            return "User does not exist in the system! ";
        } else {
            String[] arrAns = ansSelect.split(",");
            if (withPassword) {
                return "User Name: " + arrAns[0] + "\n"
                        + "Password: " + arrAns[1].substring(1);

            }
            else {
                return "User Name: " + arrAns[0] + "\n";
            }
        }
    }
    public ObservableList<Vacation> getAllAvailableVacations(){
        String[] fields = new String[TblFields.enumDict.get("vacationFields").size()];
        fields[10] = Vacation.VacationStatus.FOR_SALE.name().toLowerCase();

        String[] allVacationsStr = sqlModel.selectFromTable(Tables.TBL_VACATIONS, fields).split("\n");
        List<Vacation> vacations = new ArrayList<Vacation>();
        for (int i = 0; i < allVacationsStr.length & allVacationsStr[0] != "" ; i++) {
            vacations.add(new Vacation(allVacationsStr[i]));
        }
        return FXCollections.observableList(vacations);
    }

}

