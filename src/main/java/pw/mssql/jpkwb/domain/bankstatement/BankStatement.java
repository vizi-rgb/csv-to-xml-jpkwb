package pw.mssql.jpkwb.domain.bankstatement;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.List;

@Embeddable
public class BankStatement {

    private String type;

    @OneToMany
    private List<BankStatementRow> rows;

}
