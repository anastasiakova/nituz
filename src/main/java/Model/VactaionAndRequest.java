package Model;

public class VactaionAndRequest {

    String startDate;
    String endDate;
    String destination;
    String userName;
    String answer;
    String reqID;

    public VactaionAndRequest(String startDate, String endDate, String destination, String userName, String answer, String reqID){
        this.startDate = startDate;
        this.endDate = endDate;
        this.destination = destination;
        this.userName = userName;
        this.answer = answer;
        this.reqID = reqID;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDestination() {
        return destination;
    }

    public String getUserName() {
        return userName;
    }

    public String getAnswer() {
        return answer;
    }

    public String getReqID() {
        return reqID;
    }

}
