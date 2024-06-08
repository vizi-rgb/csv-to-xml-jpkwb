package pw.mssql.jpkwb.domain;

import jakarta.persistence.*;
import lombok.*;
import pw.mssql.jpkwb.domain.balance.BalanceChange;
import pw.mssql.jpkwb.domain.bankstatement.BankStatement;
import pw.mssql.jpkwb.domain.bankstatement.BankStatementCtrl;
import pw.mssql.jpkwb.domain.heading.Heading;
import pw.mssql.jpkwb.domain.subject.Subject;

@Entity
@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JpkWb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Heading heading;

    @ManyToOne(cascade = CascadeType.ALL)
    private Subject subject;

    private String accountNumber;

    @Embedded
    private BalanceChange balanceChange;

    @Embedded
    private BankStatement bankStatement;

    @Embedded
    private BankStatementCtrl bankStatementCtrl;
}
