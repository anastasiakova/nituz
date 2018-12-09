package Model;

import java.sql.PreparedStatement;

public class Request implements ISQLable{
    private static int counter = 1;
    private String r_ID;
    private String r_buyer;
    private String r_seller;
    private String r_answer;
    private String vacationID;
    private String primaryKeyName = "r_ID";
    private String tableName = "tbl_requests";

    public Request(String r_buyer, String r_seller, String answer, String vacationID) {
        this.r_ID = Integer.toString(counter);
        counter++;
        this.r_buyer = r_buyer;
        this.r_seller = r_seller;
        this.r_answer = answer;
        this.vacationID = vacationID;
    }


//    private String tableFields = "tbl_requests("
//            + TblFields.r.toString().toLowerCase() + ", "
//            +  TblFields.PWD.toString().toLowerCase() + ", "
//            + TblFields.BIRTHDAY.toString().toLowerCase() + ", "
//            + TblFields.PRIVATENAME.toString().toLowerCase() +", "
//            + TblFields.LASTNAME.toString().toLowerCase() +", "
//            + TblFields.CITY.toString().toLowerCase()
//            + ") VALUES(?,?,?,?,?,?)";

    @Override
    public String getPrimaryKey() {
        return this.r_ID;
    }

    @Override
    public String getPrimaryKeyName() {
        return this.primaryKeyName;
    }

    @Override
    public void insertRecordToTable(PreparedStatement pstmt) {

    }

    @Override
    public String getTableFields() {
        return null;
    }

    @Override
    public String getFieldsSQLWithValues() {
        return null;
    }

    @Override
    public String getTableName() {
        return this.tableName;
    }
}
