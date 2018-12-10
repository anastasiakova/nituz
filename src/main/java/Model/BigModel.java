package Model;

public class BigModel {

    public static void CreatePaymentAndUpdateVacation(Payments newPayment){
        SQLModel sql = SQLModel.GetInstance();
        //sql.insertRecordToTable(Tables.TBL_PAYMENTS.toString().toLowerCase(), newPayment);
        String approvedReqId = newPayment.get_aprovedRequest();
        Request
    }

    public static void CreateRequestAndUpdateVacation(Request newRequest) {
    }
}
