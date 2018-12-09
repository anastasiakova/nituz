package Model;

public class TblFields {
        static enum userFields{
                USERNAME,
                PWD,
                BIRTHDAY,
                PRIVATENAME,
                LASTNAME,
                CITY,
        }

        static enum requestTblFields {
                r_ID,
                r_buyerID,
                r_sellerID,
                r_answer,
                vacationID,
        }

        static enum vacationFields{
                VACATION_ID,
                START_DATE,
                END_DATE,
                DESTINATION,
                AVIATION_COMPANY,
                NUM_OF_TICKETS,
                TICKET_TYPE,
                BAGGAGE,
                ROUND_TRIP,
                VACATION_TYPE,
                VACATION_STATUS,
                SLEEPING_ARRANGEMENTS
        }

        static enum paymentsTblFields{
                PAYMENT_ID,
                REQUEST_ID,
                TIMESTAMP,
                STATUS
        }

}

