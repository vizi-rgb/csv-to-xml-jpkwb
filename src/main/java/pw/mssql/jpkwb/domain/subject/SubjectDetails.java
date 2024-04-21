package pw.mssql.jpkwb.domain.subject;

import jakarta.persistence.Embeddable;

@Embeddable
public class SubjectDetails {

    private String NIP;
    private String fullName;
    private String REGON;
}
