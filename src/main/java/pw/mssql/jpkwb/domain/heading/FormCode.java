package pw.mssql.jpkwb.domain.heading;

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
public class FormCode {

    private String systemCode;
    private String schemaVersion;
}
