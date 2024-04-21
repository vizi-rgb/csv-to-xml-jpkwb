package pw.mssql.jpkwb.domain.bankstatement;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class BankStatementCtrl {
    private Integer rowsCount;
    private BigDecimal totalCredit;
    private BigDecimal totalDebit;
}
