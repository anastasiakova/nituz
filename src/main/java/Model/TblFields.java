package Model;

import java.util.*;

public class TblFields {

    public static Map<String, List<String>> enumDict = new HashMap<String, List<String>>();

    public static void init() {
        List<String> enumList = new ArrayList<String>();
        enumList = Arrays.asList("username", "pwd", "birthday", "privatename",
                "lastname", "city", "id");
        enumDict.put("userFields", enumList);
        enumList = Arrays.asList("r_id", "r_buyerid", "r_sellerid", "vacationid",
                "r_answer");
        enumDict.put("requestTblFields", enumList);
        enumList = Arrays.asList("vacation_id", "start_date", "end_date", "destination", "aviation_company",
                "num_of_tickets", "ticket_type", "baggage", "round_trip", "vacation_type",
                "vacation_status", "sleeping_arrangements", "owner_id");
        enumDict.put("vacationFields", enumList);
        enumList = Arrays.asList("t_id", "initializerID", "askedId", "initializerVacationId", "askedVacationId");
        enumDict.put("tradeFields", enumList);
//        enumList = Arrays.asList("payment_id", "request_id", "timestamp", "status");
//        enumDict.put("paymentsTblFields", enumList);
    }
}