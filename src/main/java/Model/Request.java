package Model;

import javafx.collections.transformation.SortedList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

// we need to check for equal request- the same buyer, seller and vecID !!!!
public class Request implements ISQLable {
    private static int counter = getMyMaxCounter();
    private String r_ID;
    private String r_buyerID;
    private String r_seller;
    private String r_answer;

    private Payments payment;
    //private String vacationID;
    private Vacation vacation;
    private String primaryKeyName = "r_ID";
    private String tableName = "tbl_requests";

    public void setPayment(Payments payment) {
        this.payment = payment;
    }

    public static int getMyMaxCounter() {
        Path currentPath = Paths.get("");
        String _path = "jdbc:sqlite:" + currentPath.toAbsolutePath().toString() + "\\dataBase.db";
        String sql = "SELECT * FROM tbl_requests;\n";
        List<Integer> res = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt  = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                 res.add(Integer.parseInt(rs.getString(1).trim()));
            }
            Collections.sort(res);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(res.size()>0) {
            return res.get(res.size() - 1);
        }
        return 0;
    }

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
                ", " + getVacationID() +
                ", " + r_answer +
                ");";
    }


    public Request(String r_buyer, String r_seller, String vacationID) {
        counter++;
        this.r_ID = Integer.toString(counter);
        this.r_buyerID = r_buyer;
        this.r_seller = r_seller;
        this.r_answer = ansStatus.pending.toString();
        setVacation(vacationID);
        payment = null;
    }

    public Request(String[] searchedRequest) {
        this.r_ID = searchedRequest[0];
        this.r_buyerID = searchedRequest[1];
        this.r_seller = searchedRequest[2];
        this.r_answer = searchedRequest[4];
        setVacation(searchedRequest[3]);
        if(r_answer == ansStatus.confirmed.name()){
            trySetPayment(r_ID,r_buyerID,r_seller);
        }
        else{
            payment = null;
        }
    }

    public Request(String request){
       this(request.split(", "));
    }

    public void upateAnswer(boolean isConfirmed) {
        if (isConfirmed) {
            this.r_answer = ansStatus.confirmed.toString();
            trySetPayment(this.r_ID, this.r_buyerID, this.r_seller);
        }
        else {
            this.r_answer = ansStatus.rejected.toString();
        }
    }

    public Payments getPayment() {
        return payment;
    }

    public Vacation getVacation() {
        return vacation;
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
                TblFields.enumDict.get("requestTblFields").get(3) + "='" + getVacationID() + "\'," +
                TblFields.enumDict.get("requestTblFields").get(4) + "='" + r_answer +
                '}';
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
        return vacation.get_vacationID();
    }

    public void setR_answer(String r_answer) {
        this.r_answer = r_answer;
    }

    private void setVacation(String vacationID){
        String[] fields = new String[TblFields.enumDict.get("vacationFields").size()];
        fields[0] = vacationID;
        String[] allRequests = SQLModel.GetInstance().selectFromTable(Tables.TBL_VACATIONS, fields).split("\n");
        this.vacation = new Vacation(allRequests[0]);
    }

    private void trySetPayment(String r_id, String r_buyerID, String r_seller) {
        String[] fields = new String[TblFields.enumDict.get("paymentsTblFields").size()];
        fields[1] = r_id;
        fields[2] = r_buyerID;
        fields[3] = r_seller;
        String allRequests = SQLModel.GetInstance().selectFromTable(Tables.TBL_PAYMENTS, fields);
        if(allRequests != "") {
            this.payment = new Payments(allRequests.split("\n")[0]);
        }
        else {
            this.payment = null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return this.r_ID == request.getR_ID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getR_ID());
    }
}
