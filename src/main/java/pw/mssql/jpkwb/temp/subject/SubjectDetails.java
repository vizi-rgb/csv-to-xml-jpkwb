package pw.mssql.jpkwb.temp.subject;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDetails {

    private String NIP;
    private String fullName;
    private String REGON;
}
