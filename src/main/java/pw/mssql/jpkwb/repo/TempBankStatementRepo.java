package pw.mssql.jpkwb.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pw.mssql.jpkwb.temp.TempBankStatement;

import java.util.List;

@Repository
public interface TempBankStatementRepo extends CrudRepository<TempBankStatement, Long> {
    List<TempBankStatement> findAll();
}
