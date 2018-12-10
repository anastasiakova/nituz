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
        ins.insertRecordToTable(vacation.getTableName(), new Vacation(new Date(), new Date(), "Berlin", "Easyjet",
                2, Vacation.TicketType.ADULT, false, true, Vacation.VacationType.URBAN,
                Vacation.VacationStatus.FOR_SALE, Vacation.VacationSleepingArrangements.NA, "Oren"));
        vacation.set_vacationStatus(Vacation.VacationStatus.IN_PROGRESS);
        ins.updateRecord(vacation);
        String[] nu = new String[TblFields.enumDict.get("vacationFields").size()];
        String st = ins.selectFromTable(Tables.TBL_VACATIONS, nu, true);
        /*username", "pwd", "birthday", "privatename",
                "lastname", "city", "bankacount","id"*/
        User user = new User("nu", "nu", new Date(), "saar", "guttman", "Nesher", "2435234", "sasaa");


    }
}
