package Model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

public class SQLModel {

    private static SQLModel _singaleDB;
    private String _path;

    private SQLModel() {
        Path currentPath = Paths.get("");
        TblFields.init();
        this._path = "jdbc:sqlite:" + currentPath.toAbsolutePath().toString() + "\\dataBase.db";
        try (Connection conn = DriverManager.getConnection(this._path)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        createUsersTable();
        createRequestsTable();
        createVactionsTable();
        //createPaymentsTable();
        //create trade table
        String trade =   "CREATE TABLE IF NOT EXISTS tbl_trades (\n" +
                TblFields.enumDict.get("tradeFields").get(0) + " text NOT NULL PRIMARY KEY,\n" +
                TblFields.enumDict.get("tradeFields").get(1) + " text NOT NULL,\n" +
                TblFields.enumDict.get("tradeFields").get(2) + " text NOT NULL,\n" +
                TblFields.enumDict.get("tradeFields").get(3) + " text NOT NULL,\n" +
                TblFields.enumDict.get("tradeFields").get(4) + " text NOT NULL\n" +
                ");";
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
             stmt.execute(trade);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        }

    public static SQLModel GetInstance() {
        if(_singaleDB != null){
            return _singaleDB;
        }
        _singaleDB = new SQLModel();
        return _singaleDB;
    }

    private void createUsersTable() {
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
            // create new Users table
            stmt.execute(User.createUsersTableSQL());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createRequestsTable() {
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
            // create new Users table
            stmt.execute(Request.createRequestsTableSQL());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createVactionsTable() {
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt = conn.createStatement()) {
            // create new Users table
            stmt.execute(Vacation.createVacationsTableSQL());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    private void createPaymentsTable() {
//        try (Connection conn = DriverManager.getConnection(_path);
//             Statement stmt = conn.createStatement()) {
//            // create new Users table
//            stmt.execute(Payments.createPaymentsTableSQL());
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        //System.out.println("Succesfully added tbl_payments!");
//    }

    public void insertRecordToTable(String table, ISQLable isqLable){
        String sql = "INSERT INTO " + isqLable.getTableFields();

        try (Connection conn = DriverManager.getConnection(_path);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            isqLable.insertRecordToTable(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertRecordToTable(String fieldsForConection, String[] fieldsData){
        String sql = "INSERT INTO " + fieldsForConection;

        try (Connection conn = DriverManager.getConnection(_path);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try {
                pstmt.setString(1, fieldsData[0]);
                pstmt.setString(2,  fieldsData[1]);
                pstmt.setString(3,  fieldsData[2]);
                pstmt.setString(4,  fieldsData[3]);
                pstmt.setString(5,  fieldsData[4]);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String selectFromTable(Tables table, String[] fields){
        boolean shouldGetAll = false;
        return selectFromTable(table, fields, shouldGetAll);
    }


    public String selectFromTable(Tables table, String[] fields, boolean shouldGetAll){
        switch (table) {
            case TBL_USERS:
                return selectFromTbl("TBL_USERS", fields, "userFields", shouldGetAll);
            case TBL_REQUESTS:
                return selectFromTbl("TBL_REQUESTS", fields, "requestTblFields", shouldGetAll);
            case TBL_VACATIONS:
                return selectFromTbl("TBL_VACATIONS", fields, "vacationFields", shouldGetAll);
            case TBL_TRADES:
                return selectFromTbl("TBL_TRADES", fields, "tradeFields", shouldGetAll);
//            case TBL_PAYMENTS:
//                return selectFromTbl("TBL_PAYMENTS", fields, "paymentsTblFields", shouldGetAll);
            default:
                return "";
        }
    }

    private String selectFromTbl(String table, String[] fields, String tblFields, boolean shouldGetAll) {
        String sql = "SELECT * FROM ";
        sql += table.toLowerCase() + "\n";
        if(!shouldGetAll) {
            sql += "WHERE ";
            boolean notFirst = false;
            for (int i = 0; i < TblFields.enumDict.get(tblFields).size(); i++) {
                if (fields[i] != "" && fields[i] != null) {
                    if (notFirst) {
                        sql += " AND ";
                    }
                    notFirst = true;
                    sql += TblFields.enumDict.get(tblFields).get(i) + "='" + fields[i] + "'";
                }
            }
        }
        sql += ";";
        String res = "";
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                for (int i = 1 ; i <= TblFields.enumDict.get(tblFields).size(); i++){
                    res += rs.getString(i) + ", ";
                }
                res += '\n';
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public void deleteRecordFromTable(ISQLable isqLable){
        String sql = "DELETE FROM " + isqLable.getTableName() + "\n";
        sql += "WHERE " + isqLable.getPrimaryKeyName() + " = '" + isqLable.getPrimaryKey() /*getPrimaryKey*/ + "';\n";

        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt  = conn.createStatement()){
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteRecordFromTable(String tblName, List<String> tblFields , String[] fieldsData ){
        String sql = "DELETE FROM " + tblName + "\n WHERE ";

        boolean notFirst = false;
        for (int i = 0; i < tblFields.size(); i++) {
            if (fieldsData[i] != "" && fieldsData[i] != null) {
                if (notFirst) {
                    sql += " AND ";
                }
                notFirst = true;
                sql += tblFields.get(i) + "='" + fieldsData[i] + "'";
            }
        }
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt  = conn.createStatement()){
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateRecord(ISQLable isqLable){
        String sql = "UPDATE " + isqLable.getTableName() + "\n";
        sql+= "SET " + isqLable.getFieldsSQLWithValues();
        sql += "WHERE " + isqLable.getPrimaryKeyName() + "='"  + isqLable.getPrimaryKey() /*getPrimaryKey*/ + "';\n";

        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt  = conn.createStatement()){
                stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}