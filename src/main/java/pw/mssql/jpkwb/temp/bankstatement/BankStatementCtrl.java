package pw.mssql.jpkwb.temp.bankstatement;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BankStatementCtrl {
    private String rowsCount;
    private String totalCredit;
    private String totalDebit;
}
