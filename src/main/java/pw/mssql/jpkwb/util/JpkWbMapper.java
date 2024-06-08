package pw.mssql.jpkwb.util;

import org.springframework.stereotype.Component;
import pw.mssql.jpkwb.domain.JpkWb;
import pw.mssql.jpkwb.domain.balance.BalanceChange;
import pw.mssql.jpkwb.domain.bankstatement.BankStatement;
import pw.mssql.jpkwb.domain.bankstatement.BankStatementCtrl;
import pw.mssql.jpkwb.domain.bankstatement.BankStatementRow;
import pw.mssql.jpkwb.domain.heading.FormCode;
import pw.mssql.jpkwb.domain.heading.Heading;
import pw.mssql.jpkwb.domain.subject.Subject;
import pw.mssql.jpkwb.domain.subject.SubjectAddress;
import pw.mssql.jpkwb.domain.subject.SubjectDetails;
import pw.mssql.jpkwb.temp.TempBankStatement;
import pw.mssql.jpkwb.temp.TempJpkWb;
import pw.mssql.jpkwb.temp.bankstatement.TempBankStatementRow;
import pw.mssql.jpkwb.temp.subject.TempSubject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.stream.Collectors;

@Component
public class JpkWbMapper {

    private final static DateTimeFormatter df = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public JpkWb map(TempJpkWb tempJpkWb, TempBankStatement tempBankStatement) {
        final var heading = mapHeading(tempJpkWb.getHeading());
        final var subject = mapSubject(tempJpkWb.getSubject());
        final var balanceChange = mapBalanceChange(tempJpkWb.getBalanceChange());
        final var bankStatement = mapBankStatement(tempBankStatement);
        final var bankStatementCtrl = mapBankStatementCtrl(tempJpkWb.getBankStatementCtrl());

        return JpkWb.builder()
            .heading(heading)
            .subject(subject)
            .accountNumber(tempJpkWb.getAccountNumber())
            .balanceChange(balanceChange)
            .bankStatement(bankStatement)
            .bankStatementCtrl(bankStatementCtrl)
            .build();
    }

    private Heading mapHeading(pw.mssql.jpkwb.temp.heading.Heading heading) {
        return Heading.builder()
            .formCode(
                FormCode.builder()
                    .systemCode(heading.getFormCode().getSystemCode())
                    .schemaVersion(heading.getFormCode().getSchemaVersion())
                    .build()
            )
            .formVariant(Short.parseShort(heading.getFormVariant()))
            .submissionPurpose(heading.getSubmissionPurpose())
            .jpkCreationDate(LocalDate.parse(heading.getJpkCreationDate(), df).atStartOfDay())
            .fromDate(LocalDate.parse(heading.getFromDate(), df))
            .toDate(LocalDate.parse(heading.getToDate(), df))
            .currencyCode(Currency.getInstance(heading.getCurrencyCode()))
            .officeCode(heading.getOfficeCode())
            .build();
    }

    private BankStatement mapBankStatement(TempBankStatement tempBankStatement) {
        return BankStatement.builder()
            .type(tempBankStatement.getType())
            .rows(tempBankStatement.getRows().stream()
                .map(this::mapBankStatementRow)
                .collect(Collectors.toList())
            )
            .build();
    }

    private BankStatementRow mapBankStatementRow(TempBankStatementRow tempBankStatementRow) {
        return BankStatementRow.builder()
            .rowNumber(Integer.parseInt(tempBankStatementRow.getRowNumber()))
            .operationDate(LocalDate.parse(tempBankStatementRow.getOperationDate(), df))
            .subjectName(tempBankStatementRow.getSubjectName())
            .operationDescription(tempBankStatementRow.getOperationDescription())
            .operationAmount(new BigDecimal(tempBankStatementRow.getOperationAmount()))
            .operationBalance(new BigDecimal(tempBankStatementRow.getOperationBalance()))
            .build();
    }

    private BalanceChange mapBalanceChange(pw.mssql.jpkwb.temp.balance.BalanceChange balanceChange) {
        final var openingBalance = balanceChange.getOpeningBalance();
        final var closingBalance = balanceChange.getClosingBalance();

        return BalanceChange.builder()
            .openingBalance(new BigDecimal(openingBalance))
            .closingBalance(new BigDecimal(closingBalance))
            .build();
    }

    private Subject mapSubject(TempSubject tempSubject) {
        return Subject.builder()
            .subjectDetails(
                SubjectDetails.builder()
                    .NIP(tempSubject.getSubjectDetails().getNIP())
                    .fullName(tempSubject.getSubjectDetails().getFullName())
                    .REGON(tempSubject.getSubjectDetails().getREGON())
                    .build()
            )
            .subjectAddress(
                SubjectAddress.builder()
                    .countryCode(tempSubject.getSubjectAddress().getCountryCode())
                    .region(tempSubject.getSubjectAddress().getRegion())
                    .county(tempSubject.getSubjectAddress().getCounty())
                    .community(tempSubject.getSubjectAddress().getCommunity())
                    .street(tempSubject.getSubjectAddress().getStreet())
                    .houseNumber(tempSubject.getSubjectAddress().getHouseNumber())
                    .apartmentNumber(tempSubject.getSubjectAddress().getApartmentNumber())
                    .city(tempSubject.getSubjectAddress().getCity())
                    .postalCode(tempSubject.getSubjectAddress().getPostalCode())
                    .postOfficeCity(tempSubject.getSubjectAddress().getPostOfficeCity())
                    .build()
            )
            .build();
    }

    private BankStatementCtrl mapBankStatementCtrl(pw.mssql.jpkwb.temp.bankstatement.BankStatementCtrl bankStatementCtrl) {
        return BankStatementCtrl.builder()
            .rowsCount(Integer.parseInt(bankStatementCtrl.getRowsCount()))
            .totalCredit(new BigDecimal(bankStatementCtrl.getTotalCredit()))
            .totalDebit(new BigDecimal(bankStatementCtrl.getTotalDebit()))
            .build();
    }
}
