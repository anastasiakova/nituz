package Model;

import java.sql.PreparedStatement;

public class Request implements ISQLable{
    private static int counter = 1;
    private String r_ID;
    private String r_buyerID;
    private String r_seller;
    private String r_answer;
    private String vacationID;
    private String primaryKeyName = "r_ID";
    private String tableName = "tbl_requests";

    private String tableFields = "tbl_requests("
            + TblFields.requestTblFields.r_ID.toString().toLowerCase() + ", "
            + TblFields.requestTblFields.r_buyerID.toString().toLowerCase() + ", "
            + TblFields.requestTblFields.r_sellerID.toString().toLowerCase() + ", "
            + TblFields.requestTblFields.r_answer.toString().toLowerCase() + ", "
            + TblFields.requestTblFields.vacationID.toString().toLowerCase() + ", "
            + ") VALUES(?,?,?,?,?)";

//
//    public static String createRequestsTableSQL(){
//        return ("CREATE TABLE IF NOT EXISTS tbl_requests (\n" +
//                TblFields.requestTblFields.r_ID.toString().toLowerCase() + " text NOT NULL PRIMARY KEY,\n" +
//                TblFields.requestTblFields.r_buyerID.toString().toLowerCase()   + " text NOT NULL,\n" +
//                TblFields.BIRTHDAY.toString().toLowerCase()   + " birthday text,\n" +
//                TblFields.PRIVATENAME.toString().toLowerCase()    + "	privateName text,\n" +
//                TblFields.LASTNAME.toString().toLowerCase()    + "	lastName text,\n" +
//                TblFields.CITY.toString().toLowerCase()    + "	city text\n" +
//                ");");
//    }


    public Request(String r_buyer, String r_seller, String answer, String vacationID) {
        this.r_ID = Integer.toString(counter);
        counter++;
        this.r_buyerID = r_buyer;
        this.r_seller = r_seller;
        this.r_answer = answer;
        this.vacationID = vacationID;
    }



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


    public String getR_ID() {
        return r_ID;
    }

    public String getR_buyerID() {
        return r_buyerID;
    }

    public String getR_seller() {
        return r_seller;
    }

    public String getR_answer() {
        return r_answer;
    }

    public String getVacationID() {
        return vacationID;
    }
}
