package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        TblFields.init();
        SQLModel ins = SQLModel.GetInstance();
        Vacation vacation = new Vacation(new Date(), new Date(), "Vienna", "el al",
                                        2, Vacation.TicketType.ADULT, false, true, Vacation.VacationType.URBAN,
                Vacation.VacationStatus.FOR_SALE, Vacation.VacationSleepingArrangements.NA, "Oren");
        ins.insertRecordToTable(vacation.getTableName(), vacation);


    }
}
