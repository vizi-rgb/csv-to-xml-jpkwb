package pw.mssql.jpkwb.temp.subject;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDetails {

    private String NIP;
    private String fullName;
    private String REGON;
}
