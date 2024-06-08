package pw.mssql.jpkwb.service.xml;

import org.springframework.stereotype.Service;
import pw.mssql.jpkwb.domain.JpkWb;
import pw.mssql.jpkwb.repo.JpkWbRepo;

import javax.xml.transform.OutputKeys;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Properties;

@Service
public class XmlReportService {

    private final Properties outputProperties;

    public XmlReportService(JpkWbRepo jpkWbRepo) {
        this.outputProperties = new Properties();
        this.outputProperties.put(OutputKeys.METHOD, "xml");
        this.outputProperties.put(OutputKeys.INDENT, "yes");
        this.outputProperties.put("{http://xml.apache.org/xslt}indent-amount", "2");
    }

    public void generateXmlReport(JpkWb jpkWb) throws FileNotFoundException {
        final var xmlOutput = new XmlOutput("JPK");

        xmlOutput
            .with(jpkWb.getHeading())
            .with(jpkWb.getSubject())
            .with(jpkWb.getAccountNumber())
            .with(jpkWb.getBalanceChange())
            .with(jpkWb.getBankStatement())
            .with(jpkWb.getBankStatementCtrl());

        final var reportName = jpkWb.getSubject().getSubjectDetails().getNIP() + "_" + jpkWb.getHeading().getFromDate() + "_" + jpkWb.getHeading().getToDate();
        final var xml = xmlOutput.get();

        final PrintWriter writer = new PrintWriter(new FileOutputStream(reportName + ".xml"));
        xml.toWriter(writer, outputProperties);
    }

}
