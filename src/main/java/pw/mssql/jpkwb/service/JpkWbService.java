package pw.mssql.jpkwb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pw.mssql.jpkwb.domain.JpkWb;
import pw.mssql.jpkwb.repo.JpkWbRepo;
import pw.mssql.jpkwb.repo.TempBankStatementRepo;
import pw.mssql.jpkwb.repo.TempJpkWbRepo;
import pw.mssql.jpkwb.service.xml.XmlReportService;
import pw.mssql.jpkwb.temp.TempBankStatement;
import pw.mssql.jpkwb.temp.TempJpkWb;
import pw.mssql.jpkwb.util.DataReader;
import pw.mssql.jpkwb.util.JpkWbMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class JpkWbService {

    private final DataReader dataReader;

    private final TempJpkWbRepo tempJpkWbRepo;
    private final TempBankStatementRepo tempBankStatementRepo;

    private final JpkWbMapper jpkWbMapper;

    private final JpkWbRepo jpkWbRepo;

    private final XmlReportService xmlReportService;
    private final ValidatorService validatorService;

    @Transactional
    public void processJpkWb() {
        try {
            saveTempJpkWb();
            validatorService.validate(tempJpkWbRepo.findAll(), tempBankStatementRepo.findAll());
            saveJpkWbFromTempJpkWb();

            final List<JpkWb> jpkWbList = jpkWbRepo.findAll();
            for (JpkWb jpkWb : jpkWbList) {
                generateXmlReport(jpkWb);
            }

        } catch (Exception e) {
            log.error("Error while processing JPKWB: {}", e.getMessage());
        }
    }

    private void saveTempJpkWb() {
        List<TempJpkWb> tempJpkWbs;
        List<TempBankStatement> tempBankStatements;

        try {
            tempJpkWbs = dataReader.readJpkWbFile();
            tempBankStatements = dataReader.readBankRowsFile();
        } catch (FileNotFoundException e) {
            log.error("File not found: {}", e.getMessage());
            return;
        } catch (IOException e) {
            log.error("Error while reading file: {}", e.getMessage());
            return;
        }

        tempJpkWbRepo.saveAll(tempJpkWbs);
        tempBankStatementRepo.saveAll(tempBankStatements);
    }

    private void saveJpkWbFromTempJpkWb() {
        final List<TempJpkWb> tempJpkWbList = tempJpkWbRepo.findAll();
        final List<TempBankStatement> tempBankStatementList = tempBankStatementRepo.findAll();

        try {
            validatorService.validate(tempJpkWbList, tempBankStatementList);
        } catch (IllegalArgumentException e) {
            log.error("Error while validating data: {}", e.getMessage());
            throw e;
        }

        tempJpkWbList
            .forEach(
                tempJpkWb -> {
                    final var tempBankStatement = tempBankStatementList
                        .stream()
                        .filter(
                            tmp -> tmp
                                .getRows()
                                .getFirst()
                                .getSubjectName()
                                .equals(tempJpkWb.getSubject().getSubjectDetails().getFullName())
                        )
                        .findFirst();

                    if (tempBankStatement.isPresent()) {
                        final var jpkWb = jpkWbMapper.map(tempJpkWb, tempBankStatement.get());
                        jpkWbRepo.save(jpkWb);
                    }
                }
            );


    }

    private void generateXmlReport(JpkWb jpkWb) throws FileNotFoundException {
        try {
            xmlReportService.generateXmlReport(jpkWb);
        } catch (FileNotFoundException e) {
            log.error("Error while generating report: {}", e.getMessage());
            throw e;
        }
    }

}
