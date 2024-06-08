package pw.mssql.jpkwb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pw.mssql.jpkwb.temp.TempBankStatement;
import pw.mssql.jpkwb.temp.TempJpkWb;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ValidatorService {

    public void validate(
        List<TempJpkWb> tempJpkWbList,
        List<TempBankStatement> tempBankStatementList
    )
        throws IllegalArgumentException {
        if (tempJpkWbList.isEmpty()) {
            throw new IllegalArgumentException("TempJpkWb list is empty");
        }

        if (tempBankStatementList.isEmpty()) {
            throw new IllegalArgumentException("TempBankStatement list is empty");
        }

        tempBankStatementList.stream().filter(
            tempBankStatement -> tempBankStatement.getRows().isEmpty()
        ).findAny().ifPresent(
            tempBankStatement -> {
                throw new IllegalArgumentException("TempBankStatement has empty rows");
            }
        );

        final var subjects = tempJpkWbList.stream()
            .map(temp -> temp.getSubject().getSubjectDetails().getFullName())
            .collect(Collectors.toSet());

        for (var subject : subjects) {

            final boolean hasCorrespondingBankStatement =
                tempBankStatementList.stream()
                    .anyMatch(
                        tempBankStatement -> tempBankStatement
                            .getRows()
                            .getFirst()
                            .getSubjectName()
                            .equals(subject)
                    );

            if (!hasCorrespondingBankStatement) {
                throw new IllegalArgumentException("TempJpkWb has no corresponding TempBankStatement (" + subject + ")");
            }
        }

        for (var statement : tempBankStatementList) {
            final var subjectName = statement.getRows().getFirst().getSubjectName();
            final var correspondingJpkWb = subjects.contains(subjectName);

            if (!correspondingJpkWb) {
                throw new IllegalArgumentException("TempBankStatement has no corresponding TempJpkWb (" + subjectName + ")");
            }
        }
    }


}
