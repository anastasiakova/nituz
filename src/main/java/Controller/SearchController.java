package Controller;

import Model.SQLModel;
import Model.Tables;
import Model.TblFields;
import Model.Vacation;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

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
                        + "Password: " + arrAns[1].substring(1)
                        + "\nB-day: " + arrAns[2].substring(1)
                        + "\nFirst Name: " + arrAns[3].substring(1)
                        + "\nLast Name: " + arrAns[4].substring(1)
                        + "\nCity: " + arrAns[5].substring(1, arrAns[5].length() - 2);
            }
            else {
                return "User Name: " + arrAns[0] + "\n"
                        + "\nB-day: " + arrAns[2].substring(1)
                        + "\nFirst Name: " + arrAns[3].substring(1)
                        + "\nLast Name: " + arrAns[4].substring(1)
                        + "\nCity: " + arrAns[5].substring(1, arrAns[5].length() - 2);
            }
        }
    }
    public void getAllAvailableVacations(){
        String[] fields = new String[TblFields.enumDict.get("vacationFields").size()];
        fields[10] = Vacation.VacationStatus.FOR_SALE.name().toLowerCase();

        String[] allVacationsStr = sqlModel.selectFromTable(Tables.TBL_VACATIONS, fields).split("\n");
        List<Vacation> vacations = new ArrayList<Vacation>();
        for (int i = 0; i < allVacationsStr.length ; i++) {
            vacations.add(new Vacation(allVacationsStr[i]));
        }
//        for (String vacation: allVacationsStr ) {
//            vacations.add(new Vacation(vacation));
//        }
        System.out.println(vacations);
        //return vacations;
    }

    public boolean isLoginValid(String username, String pwd) {
        String[] fields = new String[TblFields.enumDict.get("userFields").size()];
        fields[0] = username;
        fields[1] = pwd;
        String ans = sqlModel.selectFromTable(Tables.TBL_USERS, fields);
        return  (ans.length() != 0);
    }
}

