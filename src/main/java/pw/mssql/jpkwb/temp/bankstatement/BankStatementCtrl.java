package pw.mssql.jpkwb.temp.bankstatement;

import jakarta.persistence.Embeddable;

@Embeddable
public class BankStatementCtrl {
    private String rowsCount;
    private String totalCredit;
    private String totalDebit;
}
