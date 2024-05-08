package pw.mssql.jpkwb.temp.bankstatement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BankStatementRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rowNumber;
    private String operationDate;
    private String subjectName;
    private String operationDescription;
    private String operationAmount;
    private String operationBalance;
}
