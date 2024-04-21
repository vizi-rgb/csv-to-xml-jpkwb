package pw.mssql.jpkwb.domain.bankstatement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class BankStatementRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rowNumber;
    private LocalDate operationDate;
    private String subjectName;
    private String operationDescription;
    private BigDecimal operationAmount;
    private BigDecimal operationBalance;
}
