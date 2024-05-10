package pw.mssql.jpkwb.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pw.mssql.jpkwb.temp.TempBankStatement;

@Repository
public interface TempBankStatementRepo extends CrudRepository<TempBankStatement, Long> {
}
