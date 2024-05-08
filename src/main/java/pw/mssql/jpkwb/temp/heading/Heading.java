package pw.mssql.jpkwb.temp.heading;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class Heading {

    @Embedded
    private FormCode formCode;
    private String formVariant;
    private String submissionPurpose;
    private String jpkCreationDate;
    private String fromDate;
    private String toDate;
    private String currencyCode;
    private String officeCode;


}
