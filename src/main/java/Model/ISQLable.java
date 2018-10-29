package Model;

import java.sql.PreparedStatement;

enum Tables {
    TBL_USERS
}

public interface ISQLable {
    String getPrimaryKey();
    String getPrimaryKeyName();
    void insertRecordToTable(PreparedStatement pstmt);
    String getTableFields();
    String getFieldsSQLWithValues();
    String getTableName();
}
