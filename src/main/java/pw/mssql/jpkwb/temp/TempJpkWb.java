package pw.mssql.jpkwb.temp;

import jakarta.persistence.*;
import lombok.*;
import pw.mssql.jpkwb.temp.balance.BalanceChange;
import pw.mssql.jpkwb.temp.bankstatement.BankStatementCtrl;
import pw.mssql.jpkwb.temp.heading.Heading;
import pw.mssql.jpkwb.temp.subject.TempSubject;

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

    @ManyToOne(cascade = CascadeType.ALL)
    private TempSubject subject;

    private String accountNumber;

    @Embedded
    private BalanceChange balanceChange;

    @Embedded
    private BankStatementCtrl bankStatementCtrl;
}
