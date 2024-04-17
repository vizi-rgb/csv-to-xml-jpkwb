package pw.mssql.jpkwb.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class SubjectAddress {
    private String countryCode;
    private String region;
    private String county;
}
