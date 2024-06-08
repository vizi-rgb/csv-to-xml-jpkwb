package pw.mssql.jpkwb.domain.bankstatement;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Embeddable
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankStatement {

    private String type;

    @OneToMany(cascade = CascadeType.ALL)
    private List<BankStatementRow> rows;

}
