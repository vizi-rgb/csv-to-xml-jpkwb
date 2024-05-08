package pw.mssql.jpkwb.temp.heading;

import jakarta.persistence.Embeddable;

@Embeddable
public class FormCode {

    private String systemCode;
    private String schemaVersion;
}
