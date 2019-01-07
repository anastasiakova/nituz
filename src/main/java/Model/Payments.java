package Model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Payments implements ISQLable {
    private static Integer _paymentId = getMyMaxCounter();
    public void set_status(Status _status) {
        this._status = _status;
    }
    public static int getMyMaxCounter() {
        Path currentPath = Paths.get("");
        String _path = "jdbc:sqlite:" + currentPath.toAbsolutePath().toString() + "\\dataBase.db";
        String sql = "SELECT * FROM tbl_payments;\n";
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
    //private String _aprovedRequest;
    private  Request _aprovedRequest;
    private String _id;
    private String _date;
    private String primaryKeyName = "payment_id";
    private String tableName = "tbl_payments";
    private Status _status;


    private enum Status {
        inProgress,
        Canceled,
        Sucssesful
    };

    public Payments (String aprovedRequest){
        this(aprovedRequest.split(", ")) ;
    }

    public Payments(String[] searchedPayment) {
        if(searchedPayment.length != 1){
            this._id = searchedPayment[0];
            set_aprovedRequest(searchedPayment[1]);
            this._date = searchedPayment[2];
            this._status = Status.valueOf(searchedPayment[3].toUpperCase());
        }
        else{
            set_aprovedRequest(searchedPayment[0]);
            _paymentId++;
            this._id = _paymentId.toString();
            this._date = new SimpleDateFormat("dd/MM/yyyy-HH:mm").format(Calendar.getInstance().getTime());
            this._status = Status.inProgress;
        }
    }

    private String tableFields = tableName + "("
            + TblFields.enumDict.get("paymentsTblFields").get(0) + ", "
            + TblFields.enumDict.get("paymentsTblFields").get(1) + ", "
            + TblFields.enumDict.get("paymentsTblFields").get(2) + ", "
            + TblFields.enumDict.get("paymentsTblFields").get(3)
            + ") VALUES(?,?,?,?)";

    public String get_id() {
        return _id;
    }

    public String get_date() {
        return _date;
    }

    public String get_aprovedRequest() {
        return _aprovedRequest.getR_ID();
    }

    public Request get_Request() {
        return _aprovedRequest;
    }

    public String get_status() {
        return _status.name().toLowerCase();
    }

    @Override
    public String getPrimaryKey() {
        return _id;
    }

    @Override
    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    @Override
    public void insertRecordToTable(PreparedStatement pstmt) {
        try {
            pstmt.setString(1, this.get_id());
            pstmt.setString(2, this.get_aprovedRequest());
            pstmt.setString(3, this.get_date());
            pstmt.setString(4, this.get_status());
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
        return TblFields.enumDict.get("paymentsTblFields").get(0) + "='" + this.get_id() +
                "'," + TblFields.enumDict.get("paymentsTblFields").get(1) + "='" + this.get_aprovedRequest() +
                "'," + TblFields.enumDict.get("paymentsTblFields").get(2) + "='" + this.get_date() +
                "'," + TblFields.enumDict.get("paymentsTblFields").get(3) + "='" + this.get_status() +
                "'\n";
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    public void ChangeStatus(Boolean isCancel){
        if(isCancel) {
            this._status = Status.Canceled;
          //change TBL
        }
        else{
            this._status = Status.Sucssesful;
            //change TBL
        }
    }

    public static String createPaymentsTableSQL(){
        return ("CREATE TABLE IF NOT EXISTS tbl_payments (\n" +
                TblFields.enumDict.get("paymentsTblFields").get(0) + " text NOT NULL PRIMARY KEY,\n" +
                TblFields.enumDict.get("paymentsTblFields").get(1)   + " text NOT NULL,\n" +
                TblFields.enumDict.get("paymentsTblFields").get(2)   + " text NOT NULL,\n" +
                TblFields.enumDict.get("paymentsTblFields").get(3)    + " text NOT NULL\n" +
                ");");
    }

    public String getPayMentFieldsSQL(){
        return "VALUES (" + get_id() +
                ", " + get_aprovedRequest() +
                ", " + get_date() +
                ", " + get_status() +
                ");";
    }

    private void set_aprovedRequest(String requestID){
        String[] fields = new String[TblFields.enumDict.get("requestTblFields").size()];
        fields[0] = requestID;
        String[] allRequests = SQLModel.GetInstance().selectFromTable(Tables.TBL_REQUESTS, fields).split("\n");
        this._aprovedRequest = new Request(allRequests[0]);
    }
}