package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;


public class User implements ISQLable {
    private String username;
    private String pwd;
    private Date birthday;
    private String privateName;
    private String lastName;
    private String city;




    private String tableFields = "tbl_users("
            + TblFields.userFields.USERNAME.toString().toLowerCase() + ", "
            +  TblFields.userFields.PWD.toString().toLowerCase() + ", "
            + TblFields.userFields.BIRTHDAY.toString().toLowerCase() + ", "
            + TblFields.userFields.PRIVATENAME.toString().toLowerCase() +", "
            + TblFields.userFields.LASTNAME.toString().toLowerCase() +", "
            + TblFields.userFields.CITY.toString().toLowerCase()
            + ") VALUES(?,?,?,?,?,?)";

    private String primaryKeyName = "username";
    private String tableName = "tbl_users";


    public static String createUsersTableSQL(){
        return ("CREATE TABLE IF NOT EXISTS tbl_users (\n" +
        TblFields.userFields.USERNAME.toString().toLowerCase() + " text NOT NULL PRIMARY KEY,\n" +
        TblFields.userFields.PWD.toString().toLowerCase()   + " pwd text NOT NULL,\n" +
        TblFields.userFields.BIRTHDAY.toString().toLowerCase()   + " birthday text,\n" +
        TblFields.userFields.PRIVATENAME.toString().toLowerCase()    + "	privateName text,\n" +
        TblFields.userFields.LASTNAME.toString().toLowerCase()    + "	lastName text,\n" +
        TblFields.userFields.CITY.toString().toLowerCase()    + "	city text\n" +
        ");");
    }

    public static String dropUsersTableSQL(){
        return "DROP TABLE IF EXISTS tbl_users;";
    }

    public String getUserFieldsSQL(){
        return "VALUES (" + username +
                ", " + pwd +
                ", " + birthday +
                ", " + privateName +
                ", " + lastName +
                ", " + city +
                ");";
    }

    public User(String username, String pwd, Date birthday, String privateName, String lastName, String city) {
        this.username = username;
        this.pwd = pwd;
        this.birthday = birthday;
        this.privateName = privateName;
        this.lastName = lastName;
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public String getPrimaryKey(){
        return getUsername();
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPrivateName() {
        return privateName;
    }

    public void setPrivateName(String privateName) {
        this.privateName = privateName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPwd(), user.getPwd()) &&
                Objects.equals(getBirthday(), user.getBirthday()) &&
                Objects.equals(getPrivateName(), user.getPrivateName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                Objects.equals(getCity(), user.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPwd(), getBirthday(), getPrivateName(), getLastName(), getCity());
    }

    @Override
    public String toString() {
        return "User{" +
                TblFields.userFields.USERNAME.toString().toLowerCase() + "='" + username + "\'," +
                TblFields.userFields.PWD.toString().toLowerCase() + "='" + pwd + "\'," +
                TblFields.userFields.BIRTHDAY.toString().toLowerCase() + "='" + birthday + "\'," +
                TblFields.userFields.PRIVATENAME.toString().toLowerCase() + "='" + privateName + "\'," +
                TblFields.userFields.LASTNAME.toString().toLowerCase() + "='" + lastName + "\'," +
                TblFields.userFields.CITY.toString().toLowerCase() + "='" + city + "\'," +
                '}';
    }

    public void insertRecordToTable(PreparedStatement pstmt) {
        try {
            pstmt.setString(1, this.getUsername());
            pstmt.setString(2, this.getPwd());
            pstmt.setString(3, this.getBirthday().toString());
            pstmt.setString(4, this.getPrivateName());
            pstmt.setString(5, this.getLastName());
            pstmt.setString(6, this.getCity());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getTableFields() {
        return tableFields;
    }

    public String getFieldsSQLWithValues() {
        return TblFields.userFields.USERNAME.toString().toLowerCase() + "='" + this.getUsername() +
                "'," + TblFields.userFields.PWD.toString().toLowerCase() + "='" + this.getPwd() +
                "'," + TblFields.userFields.BIRTHDAY.toString().toLowerCase() + "='" + this.getBirthday() +
                "'," + TblFields.userFields.PRIVATENAME.toString().toLowerCase() + "='" + this.getPrivateName() +
                "'," + TblFields.userFields.LASTNAME.toString().toLowerCase() + "='" + this.getLastName() +
                "'," + TblFields.userFields.CITY.toString().toLowerCase() + "='" + this.getCity() +
                "'\n";
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

}
