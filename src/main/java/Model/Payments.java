package Model;

import org.omg.PortableInterceptor.SUCCESSFUL;

import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Payments implements ISQLable {
    private static Integer _paymentId = 0;

    private String _aprovedRequest;
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
        this._aprovedRequest = aprovedRequest;
        _paymentId++;
        this._id = _paymentId.toString();
        this._date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        this._status = Status.inProgress;
    }

    public Payments(String id, String request, String date, int status) {
        this._id = id;
        this._aprovedRequest = request;
        this._date = date;
        this._status = Status.values()[status];
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
        return _aprovedRequest;
    }

    public Status get_status() {
        return _status;
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
            pstmt.setString(4, this.get_status().name());
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
                TblFields.enumDict.get("paymentsTblFields").get(0) + " NOT NULL PRIMARY KEY,\n" +
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
}