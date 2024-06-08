package pw.mssql.jpkwb.service.xml;

import com.jamesmurty.utils.XMLBuilder2;
import pw.mssql.jpkwb.domain.balance.BalanceChange;
import pw.mssql.jpkwb.domain.bankstatement.BankStatement;
import pw.mssql.jpkwb.domain.bankstatement.BankStatementCtrl;
import pw.mssql.jpkwb.domain.bankstatement.BankStatementRow;
import pw.mssql.jpkwb.domain.heading.Heading;
import pw.mssql.jpkwb.domain.subject.Subject;

class XmlOutput {

    private XMLBuilder2 xmlBuilder;

    public XmlOutput(String text) {
        xmlBuilder = XMLBuilder2.create(text);
    }

    public XmlOutput with(Heading heading) {
        xmlBuilder = xmlBuilder
            .e("Naglowek")
            .e("KodFormularza").t(heading.getFormCode().getSystemCode()).up()
            .e("WariantFormularza").t(heading.getFormVariant().toString()).up()
            .e("CelZlozenia").t(heading.getSubmissionPurpose()).up()
            .e("DataWytworzeniaJPK").t(heading.getJpkCreationDate().toString()).up()
            .e("DataOd").t(heading.getFromDate().toString()).up()
            .e("DataDo").t(heading.getToDate().toString()).up()
            .e("DomyslnyKodWaluty").t(heading.getCurrencyCode().getCurrencyCode()).up()
            .e("KodUrzedu").t(heading.getOfficeCode()).up().up();

        return this;
    }

    public XmlOutput with(Subject subject) {
        xmlBuilder = xmlBuilder
            .e("Podmiot1")
            .e("IdentyfikatorPodmiotu")
            .e("NIP").t(subject.getSubjectDetails().getNIP()).up()
            .e("PelnaNazwa").t(subject.getSubjectDetails().getFullName()).up()
            .e("REGON").t(subject.getSubjectDetails().getREGON()).up().up()
            .e("AdresPodmiotu")
            .e("KodKraju").t(subject.getSubjectAddress().getCountryCode()).up()
            .e("Wojewodztwo").t(subject.getSubjectAddress().getRegion()).up()
            .e("Powiat").t(subject.getSubjectAddress().getCounty()).up()
            .e("Gmina").t(subject.getSubjectAddress().getCommunity()).up()
            .e("Ulica").t(subject.getSubjectAddress().getStreet()).up()
            .e("NrDomu").t(subject.getSubjectAddress().getHouseNumber()).up()
            .e("NrLokalu").t(subject.getSubjectAddress().getApartmentNumber()).up()
            .e("Miejscowosc").t(subject.getSubjectAddress().getCity()).up()
            .e("KodPocztowy").t(subject.getSubjectAddress().getPostalCode()).up()
            .e("Poczta").t(subject.getSubjectAddress().getPostOfficeCity()).up().up()
            .up();

        return this;
    }

    public XmlOutput with(String accountNumber) {
        xmlBuilder = xmlBuilder
            .e("NumerRachunku").t(accountNumber).up();

        return this;
    }

    public XmlOutput with(BalanceChange balanceChange) {
        xmlBuilder = xmlBuilder
            .e("Salda")
            .e("SaldoPoczatkowe").t(balanceChange.getOpeningBalance().toString()).up()
            .e("SaldoKoncowe").t(balanceChange.getClosingBalance().toString()).up().up();

        return this;
    }

    public XmlOutput with(BankStatement bankStatement) {
        xmlBuilder = xmlBuilder
            .e("WyciagWiersz");

        for (BankStatementRow row : bankStatement.getRows()) {
            xmlBuilder = xmlBuilder
                .e("NumerWiersza").t(row.getRowNumber().toString()).up()
                .e("DataOperacji").t(row.getOperationDate().toString()).up()
                .e("NazwaPodmiotu").t(row.getSubjectName()).up()
                .e("OpisOperacji").t(row.getOperationDescription()).up()
                .e("KwotaOperacji").t(row.getOperationAmount().toString()).up()
                .e("SaldoOperacji").t(row.getOperationBalance().toString()).up();
        }

        xmlBuilder = xmlBuilder.e("typ").t("G").up().up();

        return this;
    }

    public XmlOutput with(BankStatementCtrl bankStatementCtrl) {
        xmlBuilder = xmlBuilder
            .e("WyciagCtrl")
            .e("LiczbaWierszy").t(bankStatementCtrl.getRowsCount().toString()).up()
            .e("SumaObciazen").t(bankStatementCtrl.getTotalDebit().toString()).up()
            .e("SumaUznan").t(bankStatementCtrl.getTotalCredit().toString()).up().up();

        return this;
    }

    public XMLBuilder2 get() {
        return this.xmlBuilder;
    }
}
