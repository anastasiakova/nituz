package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

// we need to check for equal request- the same buyer, seller and vecID !!!!
public class Request implements ISQLable {
    private static int counter = 1;
    private String r_ID;
    private String r_buyerID;
    private String r_seller;
    private String r_answer;
    private String vacationID;
    private String primaryKeyName = "r_ID";
    private String tableName = "tbl_requests";

    private enum ansStatus {
        confirmed,
        pending,
        rejected
    }

    private String tableFields = "tbl_requests("
            + TblFields.enumDict.get("requestTblFields").get(0) + ", "
            + TblFields.enumDict.get("requestTblFields").get(1) + ", "
            + TblFields.enumDict.get("requestTblFields").get(2) + ", "
            + TblFields.enumDict.get("requestTblFields").get(3) + ", "
            + TblFields.enumDict.get("requestTblFields").get(4)
            + ") VALUES(?,?,?,?,?)";


    public static String createRequestsTableSQL() {
        return ("CREATE TABLE IF NOT EXISTS tbl_requests (\n" +
                TblFields.enumDict.get("requestTblFields").get(0) + " text NOT NULL PRIMARY KEY,\n" +
                TblFields.enumDict.get("requestTblFields").get(1) + " text NOT NULL,\n" +
                TblFields.enumDict.get("requestTblFields").get(2) + " text NOT NULL,\n" +
                TblFields.enumDict.get("requestTblFields").get(3) + " text NOT NULL,\n" +
                TblFields.enumDict.get("requestTblFields").get(4) + " text NOT NULL\n" +
                ");");
    }

    public static String dropUsersTableSQL() {
        return "DROP TABLE IF EXISTS tbl_requests;";
    }

    public String getRequestsFieldsSQL() {
        return "VALUES (" + r_ID +
                ", " + r_buyerID +
                ", " + r_seller +
                ", " + vacationID +
                ", " + r_answer +
                ");";
    }


    public Request(String r_buyer, String r_seller, String vacationID) {
        this.r_ID = Integer.toString(counter);
        counter++;
        this.r_buyerID = r_buyer;
        this.r_seller = r_seller;
        this.r_answer = ansStatus.pending.toString();
        this.vacationID = vacationID;
    }

    public void upateAnswer(boolean isConfirmed) {
        if (isConfirmed)
            this.r_answer = ansStatus.confirmed.toString();
        else
            this.r_answer = ansStatus.rejected.toString();
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
        try {
            pstmt.setString(1, this.getR_ID());
            pstmt.setString(2, this.getR_buyerID());
            pstmt.setString(3, this.getR_seller().toString());
            pstmt.setString(4, this.getVacationID());
            pstmt.setString(5, this.getR_answer());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getTableFields() {
        return tableFields;
    }

    @Override
    public String getFieldsSQLWithValues() {
        return TblFields.enumDict.get("requestTblFields").get(0) + "='" + this.getR_ID() +
                "'," + TblFields.enumDict.get("requestTblFields").get(1) + "='" + this.getR_buyerID() +
                "'," + TblFields.enumDict.get("requestTblFields").get(2) + "='" + this.getR_seller() +
                "'," + TblFields.enumDict.get("requestTblFields").get(3) + "='" + this.getVacationID() +
                "'," + TblFields.enumDict.get("requestTblFields").get(4) + "='" + this.getR_answer() +
                "'\n";
    }

    @Override
    public String getTableName() {
        return this.tableName;
    }

    public String toString() {
        return "Request{" +
                TblFields.enumDict.get("requestTblFields").get(0) + "='" + r_ID + "\'," +
                TblFields.enumDict.get("requestTblFields").get(1) + "='" + r_buyerID + "\'," +
                TblFields.enumDict.get("requestTblFields").get(2) + "='" + r_seller + "\'," +
                TblFields.enumDict.get("requestTblFields").get(3) + "='" + vacationID + "\'," +
                TblFields.enumDict.get("requestTblFields").get(4) + "='" + r_answer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request r = (Request) o;
        return Objects.equals(getR_ID(), r.getR_ID()) &&
                Objects.equals(getR_buyerID(), r.getR_buyerID()) &&
                Objects.equals(getR_seller(), r.getR_seller()) &&
                Objects.equals(getVacationID(), r.getVacationID()) &&
                Objects.equals(getR_answer(), r.getR_answer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getR_ID(), getR_buyerID(), getR_seller(), getVacationID(), getR_answer());
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
