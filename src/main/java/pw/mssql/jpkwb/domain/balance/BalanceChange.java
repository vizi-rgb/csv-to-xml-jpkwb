package pw.mssql.jpkwb.domain.balance;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Embeddable
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BalanceChange {

    private BigDecimal openingBalance;
    private BigDecimal closingBalance;
}
