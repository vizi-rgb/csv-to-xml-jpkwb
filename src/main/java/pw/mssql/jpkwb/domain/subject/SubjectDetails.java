package pw.mssql.jpkwb.domain.subject;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDetails {

    private String NIP;
    private String fullName;
    private String REGON;
}
