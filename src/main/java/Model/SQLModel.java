package Model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class SQLModel {

    private static SQLModel _singaleDB;
    private String _path;

    private SQLModel() {
        Path currentPath = Paths.get("");

        this._path = "jdbc:sqlite:" + currentPath.toAbsolutePath().toString() + "\\dataBase.db";
        try (Connection conn = DriverManager.getConnection(this._path)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                // for check
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        createUsersTable();
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
        System.out.println("Succesfully added tbl_users!");
    }

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

    public String selectFromTable(Tables table, String[] fields){
        switch (table) {
            case TBL_USERS:
                return selectFromUsersTbl(fields);
            default:
                return "";
        }
    }

    private String selectFromUsersTbl(String[] fields) {
        String sql = "SELECT * FROM tbl_users\n";
        sql += "WHERE ";
        boolean notFirst = false;
        for (int i = 0; i < TblFields.userFields.values().length; i++) {
            if (fields[i] != "" && fields[i]!= null) {
                if(notFirst){
                    sql += " AND ";
                }
                notFirst = true;
                sql += TblFields.userFields.values()[i].toString().toLowerCase() + "='" + fields[i] + "'";
            }
        }
        sql += ";";
        //System.out.println(sql);
        String res = "";
        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                res += rs.getString(1) + ", ";
                res += rs.getString(2) + ", ";
                res += rs.getString(3) + ", ";
                res += rs.getString(4) + ", ";
                res += rs.getString(5) + ", ";
                res += rs.getString(6) + ". ";
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
             Statement stmt  = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateRecord(ISQLable isqLable){
        String sql = "UPDATE " + isqLable.getTableName() + "\n";
        sql+= "SET " + isqLable.getFieldsSQLWithValues();
        sql += "WHERE " + isqLable.getPrimaryKeyName() + "='"  + isqLable.getPrimaryKey() /*getPrimaryKey*/ + "';\n";

        try (Connection conn = DriverManager.getConnection(_path);
             Statement stmt  = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}