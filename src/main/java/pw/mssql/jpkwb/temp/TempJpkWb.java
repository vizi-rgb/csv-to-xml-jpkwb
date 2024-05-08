package pw.mssql.jpkwb.temp;

import jakarta.persistence.*;
import lombok.*;
import pw.mssql.jpkwb.temp.balance.BalanceChange;
import pw.mssql.jpkwb.temp.bankstatement.BankStatement;
import pw.mssql.jpkwb.temp.bankstatement.BankStatementCtrl;
import pw.mssql.jpkwb.temp.heading.Heading;
import pw.mssql.jpkwb.temp.subject.Subject;

@Entity
@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TempJpkWb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Heading heading;

    @ManyToOne
    private Subject subject;

    private String accountNumber;

    @Embedded
    private BalanceChange balanceChange;

    @Embedded
    private BankStatement bankStatement;

    @Embedded
    private BankStatementCtrl bankStatementCtrl;
}
