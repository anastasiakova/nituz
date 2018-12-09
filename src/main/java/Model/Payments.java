package Model;

import org.omg.PortableInterceptor.SUCCESSFUL;

import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Payments implements ISQLable {
//need to know the the other user bank ditails
    private static int _requestId = 0;

    private Request _aprovedRequest;
    private Integer _id;
    private String _date;
    private String primaryKeyName = "payment_id";
    private String tableName = "tbl_payment";

    private Status _status;
    private enum Status {
        inProgress,
        Canceled,
        Sucssesful
    };
    public Payments (Request aprovedRequest){
        this._aprovedRequest = aprovedRequest;
        _requestId++;
        this._id = _requestId;
        this._date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        this._status = Status.inProgress;
    }
    private String tableFields = tableName + "("
            + TblFields.paymentsTblFields.PAYMENT_ID.name().toLowerCase() + ", "
            + TblFields.paymentsTblFields.REQUEST_ID.name().toLowerCase() + ", "
            + TblFields.paymentsTblFields.TIMESTAMP.name().toLowerCase() + ", "
            + TblFields.paymentsTblFields.STATUS.name().toLowerCase()
            + ") VALUES(?,?,?,?)";

    public String get_date() {
        return _date;
    }

    public Status get_status() {
        return _status;
    }

    @Override
    public String getPrimaryKey() {
        return _id.toString();
    }

    @Override
    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    @Override
    public void insertRecordToTable(PreparedStatement pstmt) {
        try {
            pstmt.setString(1, this.getPrimaryKey());
            pstmt.setString(2, this._aprovedRequest.getPrimaryKey());
            pstmt.setString(3, this.get_date());
            pstmt.setString(4, this.get_status().name());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
}
