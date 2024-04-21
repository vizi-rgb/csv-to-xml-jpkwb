package pw.mssql.jpkwb.domain.balance;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class BalanceChange {

    private BigDecimal openingBalance;
    private BigDecimal closingBalance;
}
