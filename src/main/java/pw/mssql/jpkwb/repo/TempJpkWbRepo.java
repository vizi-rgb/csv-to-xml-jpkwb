package pw.mssql.jpkwb.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pw.mssql.jpkwb.temp.TempJpkWb;

@Repository
public interface TempJpkWbRepo extends CrudRepository<TempJpkWb, Long> {
}
