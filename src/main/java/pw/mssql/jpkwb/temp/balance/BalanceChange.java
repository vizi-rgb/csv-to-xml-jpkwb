package pw.mssql.jpkwb.temp.balance;

import jakarta.persistence.Embeddable;

@Embeddable
public class BalanceChange {

    private String openingBalance;
    private String closingBalance;
}
