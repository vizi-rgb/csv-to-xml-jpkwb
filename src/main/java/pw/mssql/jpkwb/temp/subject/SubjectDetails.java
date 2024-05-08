package pw.mssql.jpkwb.temp.subject;

import jakarta.persistence.Embeddable;

@Embeddable
public class SubjectDetails {

    private String NIP;
    private String fullName;
    private String REGON;
}
