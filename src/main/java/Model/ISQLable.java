package Model;

import java.sql.PreparedStatement;

//When adding a new table, add the exact table name to the Tables Enum!

public interface ISQLable {
    String getPrimaryKey();
    String getPrimaryKeyName();
    void insertRecordToTable(PreparedStatement pstmt);
    String getTableFields();
    String getFieldsSQLWithValues();
    String getTableName();
}
