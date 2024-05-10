package pw.mssql.jpkwb.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pw.mssql.jpkwb.temp.TempBankStatement;
import pw.mssql.jpkwb.temp.TempJpkWb;
import pw.mssql.jpkwb.temp.balance.BalanceChange;
import pw.mssql.jpkwb.temp.bankstatement.BankStatementCtrl;
import pw.mssql.jpkwb.temp.bankstatement.TempBankStatementRow;
import pw.mssql.jpkwb.temp.heading.FormCode;
import pw.mssql.jpkwb.temp.heading.Heading;
import pw.mssql.jpkwb.temp.subject.SubjectAddress;
import pw.mssql.jpkwb.temp.subject.SubjectDetails;
import pw.mssql.jpkwb.temp.subject.TempSubject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
@Slf4j
public class DataReader {

    private final static String BANK_ROWS_FILE = "bank_rows.csv";
    private final static String JPK_WB_FILE = "jpk_wb.csv";
    private final static String SEPARATOR = ",";

    public ArrayList<TempJpkWb> readJpkWbFile() throws IOException {
        File file = new File(JPK_WB_FILE);
        BufferedReader br = new BufferedReader(new FileReader(file));
        final ArrayList<TempJpkWb> tempJpkWbs = new ArrayList<>();
        log.info("Reading file: {}", file.getAbsolutePath());

        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(SEPARATOR);
            final TempJpkWb tempJpkWb = tempJpkWbFromValues(values);
            tempJpkWbs.add(tempJpkWb);
        }

        return tempJpkWbs;
    }

    public ArrayList<TempBankStatement> readBankRowsFile() throws IOException {
        File file = new File(BANK_ROWS_FILE);
        BufferedReader br = new BufferedReader(new FileReader(file));
        final ArrayList<TempBankStatement> tempBankStatements = new ArrayList<>();
        final Map<String, ArrayList<TempBankStatementRow>> tempBankStatementRows = new HashMap<>();
        log.info("Reading file: {}", file.getAbsolutePath());

        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(SEPARATOR);
            final TempBankStatementRow tempRow = tempBankStatementRowFromValues(values);

            final String subjectName = tempRow.getSubjectName();
            final List<TempBankStatementRow> subjectRows = tempBankStatementRows.get(subjectName);

            if (subjectRows == null) {
                final ArrayList<TempBankStatementRow> newSubjectRows = new ArrayList<>();
                newSubjectRows.add(tempRow);
                tempBankStatementRows.put(subjectName, newSubjectRows);
            } else {
                subjectRows.add(tempRow);
            }
        }

        final Set<String> keys = tempBankStatementRows.keySet();

        for (String key : keys) {
            final List<TempBankStatementRow> rows = tempBankStatementRows.get(key);

            final TempBankStatement tempBankStatement = TempBankStatement.builder()
                .type("G")
                .rows(rows)
                .build();

            tempBankStatements.add(tempBankStatement);
        }

        return tempBankStatements;
    }

    private TempBankStatementRow tempBankStatementRowFromValues(String[] values) {
        return TempBankStatementRow.builder()
            .rowNumber(values[0])
            .operationDate(values[1])
            .subjectName(values[2])
            .operationDescription(values[3])
            .operationAmount(values[4])
            .operationBalance(values[5])
            .build();
    }

    private TempJpkWb tempJpkWbFromValues(String[] values) {
        final Heading heading = Heading.builder()
            .formCode(
                FormCode.builder()
                    .systemCode(values[0])
                    .schemaVersion(values[1])
                    .build()
            )
            .formVariant(values[2])
            .submissionPurpose(values[3])
            .jpkCreationDate(values[4])
            .fromDate(values[5])
            .toDate(values[6])
            .currencyCode(values[7])
            .officeCode(values[8])
            .build();

        final TempSubject subject = TempSubject.builder()
            .subjectDetails(
                SubjectDetails.builder()
                    .NIP(values[9])
                    .fullName(values[10])
                    .REGON(values[11])
                    .build()
            )
            .subjectAddress(
                SubjectAddress.builder()
                    .countryCode(values[12])
                    .region(values[13])
                    .county(values[14])
                    .community(values[15])
                    .street(values[16])
                    .houseNumber(values[17])
                    .apartmentNumber(values[18])
                    .city(values[19])
                    .postalCode(values[20])
                    .postOfficeCity(values[21])
                    .build()
            )
            .build();

        final String accountNumber = values[22];

        final BalanceChange balanceChange = BalanceChange.builder()
            .openingBalance(values[23])
            .closingBalance(values[24])
            .build();

        final BankStatementCtrl bankStatementCtrl = BankStatementCtrl.builder()
            .rowsCount(values[25])
            .totalCredit(values[26])
            .totalDebit(values[27])
            .build();

        return TempJpkWb.builder()
            .heading(heading)
            .subject(subject)
            .accountNumber(accountNumber)
            .balanceChange(balanceChange)
            .bankStatementCtrl(bankStatementCtrl)
            .build();
    }
}
