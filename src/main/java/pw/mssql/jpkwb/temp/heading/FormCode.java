package pw.mssql.jpkwb.temp.heading;

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
public class FormCode {

    private String systemCode;
    private String schemaVersion;
}
