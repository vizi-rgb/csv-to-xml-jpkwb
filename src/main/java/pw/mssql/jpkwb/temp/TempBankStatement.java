package pw.mssql.jpkwb.temp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pw.mssql.jpkwb.temp.bankstatement.TempBankStatementRow;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TempBankStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TempBankStatementRow> rows;

}
