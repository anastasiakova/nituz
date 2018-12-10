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
    private String bankAcount;
    private String creditCard;
    private String id;

    private String tableFields = "tbl_users("
            + TblFields.enumDict.get("userFields").get(0) + ", "//user name
            + TblFields.enumDict.get("userFields").get(1) + ", "//pwd
            + TblFields.enumDict.get("userFields").get(2) + ", "//birthday
            + TblFields.enumDict.get("userFields").get(3) +", "//private name
            + TblFields.enumDict.get("userFields").get(4) +", "//last name
            + TblFields.enumDict.get("userFields").get(5)//city
            + TblFields.enumDict.get("userFields").get(6) + ", "//bank
            + TblFields.enumDict.get("userFields").get(7) + ", "//credit card
            + TblFields.enumDict.get("userFields").get(8)//id
            + ") VALUES(?,?,?,?,?,?,?,?,?)";

    private String primaryKeyName = "username";
    private String tableName = "tbl_users";


    public static String createUsersTableSQL(){
        return ("CREATE TABLE IF NOT EXISTS tbl_users (\n" +
                TblFields.enumDict.get("userFields").get(0) + " text NOT NULL PRIMARY KEY,\n" +
                TblFields.enumDict.get("userFields").get(1) + " text NOT NULL,\n" +
                TblFields.enumDict.get("userFields").get(2) + " text,\n" +
                TblFields.enumDict.get("userFields").get(3) + "	text,\n" +
                TblFields.enumDict.get("userFields").get(4) + "	text,\n" +
                TblFields.enumDict.get("userFields").get(5) + "	text\n" +
                TblFields.enumDict.get("userFields").get(6) + "	text\n" +
                TblFields.enumDict.get("userFields").get(7) + "	text\n" +
                TblFields.enumDict.get("userFields").get(8) + "	text\n" +
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
                ", " + bankAcount +
                ", " + creditCard +
                ", " + id +
                ");";
    }

    public User(String username, String pwd, Date birthday, String privateName, String lastName, String city, String bankAcount, String creditCard, String id) {
        this.username = username;
        this.pwd = pwd;
        this.birthday = birthday;
        this.privateName = privateName;
        this.lastName = lastName;
        this.city = city;
        this.bankAcount = bankAcount;
        this.creditCard = creditCard;
        this.id = id;
    }
    public User(String username, String pwd, String birthday, String privateName, String lastName, String city, String bankAcount, String creditCard, String id) {
        this.username = username;
        this.pwd = pwd;
        this.birthday = new Date(birthday);
        this.privateName = privateName;
        this.lastName = lastName;
        this.city = city;
        this.bankAcount = bankAcount;
        this.creditCard = creditCard;
        this.id = id;
    }

    public User(String user){
        String[] serchedUser = user.split(",");
        new User(serchedUser[0],serchedUser[1],serchedUser[2],
                serchedUser[3],serchedUser[4],serchedUser[5],serchedUser[6],
                serchedUser[7],serchedUser[8]);
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

    public String getBankAcount() {
        return bankAcount;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public String getId() {
        return id;
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
                Objects.equals(getCity(), user.getCity())&&
                Objects.equals(getBankAcount(), user.getBankAcount())&&
                Objects.equals(getCreditCard(), user.getCreditCard())&&
                Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPwd(), getBirthday(), getPrivateName(), getLastName(), getCity(),getBankAcount(),getCreditCard(),getId());
    }

    @Override
    public String toString() {
        return "User{" +
                TblFields.enumDict.get("userFields").get(0) + "='" + username + "\'," +
                TblFields.enumDict.get("userFields").get(1) + "='" + pwd + "\'," +
                TblFields.enumDict.get("userFields").get(2) + "='" + birthday + "\'," +
                TblFields.enumDict.get("userFields").get(3) + "='" + privateName + "\'," +
                TblFields.enumDict.get("userFields").get(4) + "='" + lastName + "\'," +
                TblFields.enumDict.get("userFields").get(5) + "='" + city + "\'," +
                TblFields.enumDict.get("userFields").get(6) + "='" + bankAcount + "\'," +
                TblFields.enumDict.get("userFields").get(7) + "='" + creditCard + "\'," +
                TblFields.enumDict.get("userFields").get(8) + "='" + id + "\'," +
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
            pstmt.setString(7, this.getBankAcount());
            pstmt.setString(8, this.getCreditCard());
            pstmt.setString(9, this.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getTableFields() {
        return tableFields;
    }

    public String getFieldsSQLWithValues() {
        return TblFields.enumDict.get("userFields").get(0) + "='" + this.getUsername() +
                "'," + TblFields.enumDict.get("userFields").get(1) + "='" + this.getPwd() +
                "'," + TblFields.enumDict.get("userFields").get(2) + "='" + this.getBirthday() +
                "'," + TblFields.enumDict.get("userFields").get(3) + "='" + this.getPrivateName() +
                "'," + TblFields.enumDict.get("userFields").get(4) + "='" + this.getLastName() +
                "'," + TblFields.enumDict.get("userFields").get(5) + "='" + this.getCity() +
                "'," + TblFields.enumDict.get("userFields").get(6) + "='" + this.getBankAcount() +
                "'," + TblFields.enumDict.get("userFields").get(7) + "='" + this.getCreditCard() +
                "'," + TblFields.enumDict.get("userFields").get(8) + "='" + this.getId() +
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
