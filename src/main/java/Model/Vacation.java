package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Vacation implements ISQLable {

    private String _vacationID;
    private Date __startDate;
    private Date _endDate;
    private String _destination;
    private String _aviationCompany;
    private int _numOfTickets;
    private TicketType _ticketType;
    private boolean _isBaggageIncluded;
    private boolean _isRoundTrip;
    private VacationType _vacationType;
    private VacationStatus _vacationStatus;
    private VacationSleepingArrangements _vacationSleepingArrangements;
    private String _ownerID;

    private String primaryKeyName = "vacationID";
    private String tableName = "tbl_vacations";

    private String tableFields = tableName + "("
            + TblFields.enumDict.get("vacationFields").get(0) + ", " //vacation_id
            + TblFields.enumDict.get("vacationFields").get(1) + ", " //start_date
            + TblFields.enumDict.get("vacationFields").get(2) + ", " //end_date
            + TblFields.enumDict.get("vacationFields").get(3) + ", " //destination
            + TblFields.enumDict.get("vacationFields").get(4) + ", " //aviation_company
            + TblFields.enumDict.get("vacationFields").get(5) + ", " //num_of_tickets
            + TblFields.enumDict.get("vacationFields").get(6) + ", " //ticket_type
            + TblFields.enumDict.get("vacationFields").get(7) + ", " //baggage
            + TblFields.enumDict.get("vacationFields").get(8) + ", " //round_trip
            + TblFields.enumDict.get("vacationFields").get(9) + ", " //vacation_type
            + TblFields.enumDict.get("vacationFields").get(10) + ", " //vacation_status
            + TblFields.enumDict.get("vacationFields").get(11) + ", " //sleeping_arragements
            + TblFields.enumDict.get("vacationFields").get(12) + ", " //owner_id
            + ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";



    private static int count = 0;
    //<editor-fold desc="Vacation enums">
    public static enum TicketType{
        ADULT,
        CHILD,
        INFANT
    }

    public static enum VacationType{
        URBAN,
        EXOTIC,
        LEISURE,
        SKY
    }

    public static enum VacationStatus{
        FOR_SALE,
        IN_PROGRESS,
        SOLD
    }

    public static enum VacationSleepingArrangements{
        NA,
        HOTEL,
        ZIMMER,
        APARTMENT
    }
    //</editor-fold>

    public static String createVacationsTableSQL(){
        return ("CREATE TABLE IF NOT EXISTS tbl_vacations (\n" +
                TblFields.enumDict.get("vacationFields").get(0) + " text NOT NULL PRIMARY KEY,\n" + //vacation_id
                TblFields.enumDict.get("vacationFields").get(1) + " text NOT NULL,\n" + //start_date
                TblFields.enumDict.get("vacationFields").get(2) + " text NOT NULL,\n" + //end_date
                TblFields.enumDict.get("vacationFields").get(3) + " text NOT NULL,\n" + //destination
                TblFields.enumDict.get("vacationFields").get(4) + " text,\n" + //aviation_company
                TblFields.enumDict.get("vacationFields").get(5) + " text NOT NULL,\n" + //num_of_tickets
                TblFields.enumDict.get("vacationFields").get(6) + " text NOT NULL,\n" + //ticket_type
                TblFields.enumDict.get("vacationFields").get(7) + " text NOT NULL,\n" + //baggage
                TblFields.enumDict.get("vacationFields").get(8) + " text NOT NULL,\n" + //round_trip
                TblFields.enumDict.get("vacationFields").get(9) + " text,\n" + //vacation_type
                TblFields.enumDict.get("vacationFields").get(10) + " text NOT NULL,\n" + //vacation_status
                TblFields.enumDict.get("vacationFields").get(11) + " text NOT NULL,\n" + //sleeping_arrangements
                TblFields.enumDict.get("vacationFields").get(12) + " text NOT NULL,\n" + //owner_id
                ");");
    }


    //<editor-fold desc=" Vacation Constructors">
    public Vacation(Date __startDate, Date _endDate,
                    String _destination, String _aviationCompany,
                    int _numOfTickets, TicketType _ticketType,
                    boolean _isBaggageIncluded, boolean _isRoundTrip,
                    VacationType _vacationType, VacationStatus _vacationStatus,
                    VacationSleepingArrangements _vacationSleepingArrangements, String _ownerID) {

        this.__startDate = __startDate;
        this._endDate = _endDate;
        this._destination = _destination;
        this._aviationCompany = _aviationCompany;
        this._numOfTickets = _numOfTickets;
        this._ticketType = _ticketType;
        this._isBaggageIncluded = _isBaggageIncluded;
        this._isRoundTrip = _isRoundTrip;
        this._vacationType = _vacationType;
        this._vacationStatus = _vacationStatus;
        this._vacationSleepingArrangements = _vacationSleepingArrangements;
        this._ownerID = _ownerID;

        count++;
        this._vacationID = String.valueOf(count);
    }

    public Vacation(Date __startDate, Date _endDate,
                    String _destination, String _aviationCompany,
                    int _numOfTickets, TicketType _ticketType,
                    boolean _isBaggageIncluded, boolean _isRoundTrip,
                    VacationType _vacationType,
                    VacationSleepingArrangements _vacationSleepingArrangements,
                    String _ownerID) {
        this.__startDate = __startDate;
        this._endDate = _endDate;
        this._destination = _destination;
        this._aviationCompany = _aviationCompany;
        this._numOfTickets = _numOfTickets;
        this._ticketType = _ticketType;
        this._isBaggageIncluded = _isBaggageIncluded;
        this._isRoundTrip = _isRoundTrip;
        this._vacationType = _vacationType;
        this._vacationSleepingArrangements = _vacationSleepingArrangements;
        this._ownerID = _ownerID;

        count++;
        this._vacationID = String.valueOf(count);

        this._vacationStatus = VacationStatus.FOR_SALE;
    }

    public Vacation(String _vacationID, Date __startDate,
                    Date _endDate, String _destination,
                    String _aviationCompany, int _numOfTickets,
                    TicketType _ticketType, boolean _isBaggageIncluded,
                    boolean _isRoundTrip, VacationType _vacationType,
                    VacationStatus _vacationStatus, VacationSleepingArrangements _vacationSleepingArrangements,
                    String _ownerID) {

        this._vacationID = _vacationID;
        this.__startDate = __startDate;
        this._endDate = _endDate;
        this._destination = _destination;
        this._aviationCompany = _aviationCompany;
        this._numOfTickets = _numOfTickets;
        this._ticketType = _ticketType;
        this._isBaggageIncluded = _isBaggageIncluded;
        this._isRoundTrip = _isRoundTrip;
        this._vacationType = _vacationType;
        this._vacationStatus = _vacationStatus;
        this._vacationSleepingArrangements = _vacationSleepingArrangements;
        this._ownerID = _ownerID;
    }

    public Vacation(Date __startDate, Date _endDate,
                    String _destination, int _numOfTickets,
                    TicketType _ticketType, boolean _isRoundTrip,
                    String ownerID) {

        this(__startDate, _endDate, _destination,
                "El Al" /*Aviation company*/, _numOfTickets,
                _ticketType, _isRoundTrip, _isRoundTrip, VacationType.URBAN,
                VacationStatus.FOR_SALE, VacationSleepingArrangements.NA, ownerID );
    }
    //</editor-fold>

    @Override
    public String getPrimaryKey() {
        return _vacationID;
    }

    @Override
    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    @Override
    public void insertRecordToTable(PreparedStatement pstmt) {
        try {
            pstmt.setString(1, this.get_vacationID());
            pstmt.setString(2, this.get__startDate().toString());
            pstmt.setString(3, this.get_endDate().toString());
            pstmt.setString(4, this.get_destination());
            pstmt.setString(5, this.get_aviationCompany());
            pstmt.setString(6, String.valueOf(this.get_numOfTickets()));
            pstmt.setString(7, this.get_ticketType().toString().toLowerCase());
            pstmt.setString(8, String.valueOf(this.is_isBaggageIncluded()));
            pstmt.setString(9, String.valueOf(this.is_isRoundTrip()));
            pstmt.setString(10, this.get_vacationType().toString().toLowerCase());
            pstmt.setString(11, this.get_vacationStatus().toString().toLowerCase());
            pstmt.setString(12, this.get_vacationSleepingArrangements().toString().toLowerCase());
            pstmt.setString(13, this.get_ownerID());
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
        return TblFields.enumDict.get("vacationFields").get(0) + "='" + this.get_vacationID() +
                "'," + TblFields.enumDict.get("vacationFields").get(1) + "='" + this.get__startDate() +
                "'," + TblFields.enumDict.get("vacationFields").get(2) + "='" + this.get_endDate() +
                "'," + TblFields.enumDict.get("vacationFields").get(3) + "='" + this.get_destination() +
                "'," + TblFields.enumDict.get("vacationFields").get(4) + "='" + this.get_aviationCompany() +
                "'," + TblFields.enumDict.get("vacationFields").get(5) + "='" + this.get_numOfTickets() +
                "'," + TblFields.enumDict.get("vacationFields").get(6) + "='" + this.get_ticketType() +
                "'," + TblFields.enumDict.get("vacationFields").get(7) + "='" + this.is_isBaggageIncluded() +
                "'," + TblFields.enumDict.get("vacationFields").get(8) + "='" + this.is_isRoundTrip() +
                "'," + TblFields.enumDict.get("vacationFields").get(9) + "='" + this.get_vacationType() +
                "'," + TblFields.enumDict.get("vacationFields").get(10) + "='" + this.get_vacationStatus() +
                "'," + TblFields.enumDict.get("vacationFields").get(11) + "='" + this.get_vacationSleepingArrangements() +
                "'," + TblFields.enumDict.get("vacationFields").get(12) + "='" + this.get_ownerID() +

                "'\n";
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacation vacation = (Vacation) o;

        return _vacationID != null ? _vacationID.equals(vacation._vacationID) : vacation._vacationID == null;
    }

    @Override
    public int hashCode() {
        return _vacationID != null ? _vacationID.hashCode() : 0;
    }

    public void set_vacationStatus(VacationStatus _vacationStatus) {
        this._vacationStatus = _vacationStatus;
    }

    //<editor-fold desc="Getters">
    public String get_vacationID() {
        return _vacationID;
    }

    public Date get__startDate() {
        return __startDate;
    }

    public Date get_endDate() {
        return _endDate;
    }

    public String get_destination() {
        return _destination;
    }

    public String get_aviationCompany() {
        return _aviationCompany;
    }

    public int get_numOfTickets() {
        return _numOfTickets;
    }

    public TicketType get_ticketType() {
        return _ticketType;
    }

    public boolean is_isBaggageIncluded() {
        return _isBaggageIncluded;
    }

    public boolean is_isRoundTrip() {
        return _isRoundTrip;
    }

    public VacationType get_vacationType() {
        return _vacationType;
    }

    public VacationStatus get_vacationStatus() {
        return _vacationStatus;
    }

    public VacationSleepingArrangements get_vacationSleepingArrangements() {
        return _vacationSleepingArrangements;
    }

    public String get_ownerID() {
        return _ownerID;
    }
    //</editor-fold>
}

