package Model;

import java.util.*;

public class TblFields {

    public static Map<String, List<String>> enumDict = new HashMap<String, List<String>>();

    public static void init() {
        List<String> enumList = new ArrayList<String>();
        enumList = Arrays.asList("username", "pwd", "birthday", "privatename",
                "lastname","city","id");
        enumDict.put("userFields", enumList);
        enumList = Arrays.asList("r_id", "r_buyerid", "r_sellerid", "vacationid",
                "r_answer");
        enumDict.put("requestTblFields", enumList);
        enumList = Arrays.asList("vacation_id", "start_date", "end_date", "destination", "aviation_company",
                "num_of_tickets", "ticket_type", "baggage", "round_trip", "vacation_type",
                "vacation_status", "sleeping_arrangements", "owner_id");
        enumDict.put("vacationFields", enumList);
        enumList = Arrays.asList("payment_id", "request_id", "timestamp", "status");
        enumDict.put("paymentsTblFields", enumList);
    }

//    static enum userFields {
//        USERNAME,
//        PWD,
//        BIRTHDAY,
//        PRIVATENAME,
//        LASTNAME,
//        CITY,
//    }
//
//    static enum requestTblFields {
//        r_ID,
//        r_buyerID,
//        r_sellerID,
//        r_answer,
//        vacationID,
//    }
//
//    static enum vacationFields {
//        VACATION_ID,
//        START_DATE,
//        END_DATE,
//        DESTINATION,
//        AVIATION_COMPANY,
//        NUM_OF_TICKETS,
//        TICKET_TYPE,
//        BAGGAGE,
//        ROUND_TRIP,
//        VACATION_TYPE,
//        VACATION_STATUS,
//        SLEEPING_ARRANGEMENTS
//    }
//
//    static enum paymentsTblFields {
//        PAYMENT_ID,
//        REQUEST_ID,
//        TIMESTAMP,
//        STATUS
//    }

}

