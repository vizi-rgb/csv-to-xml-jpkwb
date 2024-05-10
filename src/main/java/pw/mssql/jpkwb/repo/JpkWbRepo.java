package pw.mssql.jpkwb.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pw.mssql.jpkwb.domain.JpkWb;

@Repository
public interface JpkWbRepo extends CrudRepository<JpkWb, Long> {
}
