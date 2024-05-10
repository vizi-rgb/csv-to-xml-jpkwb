package pw.mssql.jpkwb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pw.mssql.jpkwb.repo.TempBankStatementRepo;
import pw.mssql.jpkwb.repo.TempJpkWbRepo;
import pw.mssql.jpkwb.temp.TempBankStatement;
import pw.mssql.jpkwb.temp.TempJpkWb;
import pw.mssql.jpkwb.util.DataReader;

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

    @Transactional
    public void saveTempJpkWb() {
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

}
