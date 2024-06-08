package pw.mssql.jpkwb.domain.bankstatement;

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
public class BankStatementCtrl {
    private Integer rowsCount;
    private BigDecimal totalCredit;
    private BigDecimal totalDebit;
}
