package Controller;

import Model.SQLModel;
import Model.Tables;
import Model.TblFields;

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
}

