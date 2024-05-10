package pw.mssql.jpkwb.temp.balance;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BalanceChange {

    private String openingBalance;
    private String closingBalance;
}
