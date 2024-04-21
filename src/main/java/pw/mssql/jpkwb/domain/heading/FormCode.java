package pw.mssql.jpkwb.domain.heading;

import jakarta.persistence.Embeddable;

@Embeddable
public class FormCode {

    private String systemCode;
    private String schemaVersion;
}
