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
            + UserTblFields.USERNAME.toString().toLowerCase() + ", "
            +  UserTblFields.PWD.toString().toLowerCase() + ", "
            + UserTblFields.BIRTHDAY.toString().toLowerCase() + ", "
            + UserTblFields.PRIVATENAME.toString().toLowerCase() +", "
            + UserTblFields.LASTNAME.toString().toLowerCase() +", "
            + UserTblFields.CITY.toString().toLowerCase()
            + ") VALUES(?,?,?,?,?,?)";

    private String primaryKeyName = "username";
    private String tableName = "tbl_users";


    public static String createUsersTableSQL(){
        return ("CREATE TABLE IF NOT EXISTS tbl_users (\n" +
        UserTblFields.USERNAME.toString().toLowerCase() + " text NOT NULL PRIMARY KEY,\n" +
        UserTblFields.PWD.toString().toLowerCase()   + " pwd text NOT NULL,\n" +
        UserTblFields.BIRTHDAY.toString().toLowerCase()   + " birthday text,\n" +
        UserTblFields.PRIVATENAME.toString().toLowerCase()    + "	privateName text,\n" +
        UserTblFields.LASTNAME.toString().toLowerCase()    + "	lastName text,\n" +
        UserTblFields.CITY.toString().toLowerCase()    + "	city text\n" +
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
                UserTblFields.USERNAME.toString().toLowerCase() + "='" + username + "\'," +
                UserTblFields.PWD.toString().toLowerCase() + "='" + pwd + "\'," +
                UserTblFields.BIRTHDAY.toString().toLowerCase() + "='" + birthday + "\'," +
                UserTblFields.PRIVATENAME.toString().toLowerCase() + "='" + privateName + "\'," +
                UserTblFields.LASTNAME.toString().toLowerCase() + "='" + lastName + "\'," +
                UserTblFields.CITY.toString().toLowerCase() + "='" + city + "\'," +
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
        return UserTblFields.USERNAME.toString().toLowerCase() + "='" + this.getUsername() +
                "'," + UserTblFields.PWD.toString().toLowerCase() + "='" + this.getPwd() +
                "'," + UserTblFields.BIRTHDAY.toString().toLowerCase() + "='" + this.getBirthday() +
                "'," + UserTblFields.PRIVATENAME.toString().toLowerCase() + "='" + this.getPrivateName() +
                "'," + UserTblFields.LASTNAME.toString().toLowerCase() + "='" + this.getLastName() +
                "'," + UserTblFields.CITY.toString().toLowerCase() + "='" + this.getCity() +
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
