package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;


public class User implements ISQLable {
    private String username;
    private String pwd;
    private Date birthday;
    private String privateName;
    private String lastName;
    private String city;
    private String id;

    private ArrayList<Request> myRequests;
    private ArrayList<Request> requestsForMe;

    private String tableFields = "tbl_users("
            + TblFields.enumDict.get("userFields").get(0) + ", "//user name
            + TblFields.enumDict.get("userFields").get(1) + ", "//pwd
            + TblFields.enumDict.get("userFields").get(2) + ", "//birthday
            + TblFields.enumDict.get("userFields").get(3) + ", "//private name
            + TblFields.enumDict.get("userFields").get(4) + ", "//last name
            + TblFields.enumDict.get("userFields").get(5) + ", "//city
            + TblFields.enumDict.get("userFields").get(6)//id
            + ") VALUES(?,?,?,?,?,?,?)";

    private String primaryKeyName = "username";
    private String tableName = "tbl_users";


    public static String createUsersTableSQL() {
        return ("CREATE TABLE IF NOT EXISTS tbl_users (\n" +
                TblFields.enumDict.get("userFields").get(0) + " text NOT NULL PRIMARY KEY,\n" +
                TblFields.enumDict.get("userFields").get(1) + " text NOT NULL,\n" +
                TblFields.enumDict.get("userFields").get(2) + " text,\n" +
                TblFields.enumDict.get("userFields").get(3) + "	text,\n" +
                TblFields.enumDict.get("userFields").get(4) + "	text,\n" +
                TblFields.enumDict.get("userFields").get(5) + "	text,\n" +
                TblFields.enumDict.get("userFields").get(6) + "	text\n" +
                ");");
    }

    public static String dropUsersTableSQL() {
        return "DROP TABLE IF EXISTS tbl_users;";
    }

    public String getUserFieldsSQL() {
        return "VALUES (" + username +
                ", " + pwd +
                ", " + birthday +
                ", " + privateName +
                ", " + lastName +
                ", " + city +
                ", " + id +
                ");";
    }

    public User(String username, String pwd, Date birthday, String privateName, String lastName,
                String city, String id) {
        this.username = username;
        this.pwd = pwd;
        this.birthday = birthday;
        this.privateName = privateName;
        this.lastName = lastName;
        this.city = city;
        this.id = id;
        setMyRequests(username);
        setRequestsForMe(username);
    }

    public User(String[] searcheUser) {
        this.username = searcheUser[0];
        this.pwd = searcheUser[1];
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.birthday = formatter.parse(searcheUser[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.privateName = searcheUser[3];
        this.lastName = searcheUser[4];
        this.city = searcheUser[5];
        this.id = searcheUser[6];
        setMyRequests(username);
        setRequestsForMe(username);
    }

    public User(String user) {
        this(user.split(", "));
    }

    public String getUsername() {
        return username;
    }

    public String getPrimaryKey() {
        return getUsername();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }


    public String getBirthday() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(birthday);
    }

    public String getPrivateName() {
        return privateName;
    }

    public ArrayList<Request> getMyRequests() {
        return myRequests;
    }

    public ArrayList<Request> getRequestsForMe() {
        return requestsForMe;
    }


    public String getLastName() {
        return lastName;
    }


    public String getCity() {
        return city;
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
                Objects.equals(getCity(), user.getCity()) &&
                Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPwd(), getBirthday(), getPrivateName(),
                getLastName(), getCity(), getId());
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
                TblFields.enumDict.get("userFields").get(6) + "='" + id + "\'," +
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
            pstmt.setString(7, this.getId());
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
                "'," + TblFields.enumDict.get("userFields").get(6) + "='" + this.getId() +
                "'\n";
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    public void UpdateMyRequsts(Request newRequest) {
        boolean isIn = false;
        for(int i = 0; i <myRequests.size();i++) {
            if (newRequest.getR_ID().equals(myRequests.get(i).getR_ID()))
                isIn = true;
        }
        if (isIn) {
            myRequests.remove(newRequest);
        }
        myRequests.add(newRequest);
    }


    private void setMyRequests(String username) {
        myRequests = new ArrayList<>();
        String[] fields = new String[TblFields.enumDict.get("requestTblFields").size()];
        fields[1] = username;
        String[] allRequests = SQLModel.GetInstance().selectFromTable(Tables.TBL_REQUESTS, fields).split("\n");
        for (int i = 0; i < allRequests.length & allRequests[0] != ""; i++) {
            myRequests.add(new Request(allRequests[i]));
        }
    }

    private void setRequestsForMe(String username) {
        requestsForMe = new ArrayList<>();
        String[] fields = new String[TblFields.enumDict.get("requestTblFields").size()];
        fields[2] = username;
        String[] allRequests = SQLModel.GetInstance().selectFromTable(Tables.TBL_REQUESTS, fields).split("\n");
        for (int i = 0; i < allRequests.length & allRequests[0] != ""; i++) {
            requestsForMe.add(new Request(allRequests[i]));
        }
    }


}
