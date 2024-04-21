package pw.mssql.jpkwb.domain.heading;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.time.LocalDateTime;
import java.util.Currency;

@Embeddable
public class Heading {

    @Embedded
    private FormCode formCode;
    private Byte formVariant;
    private String submissionPurpose;
    private LocalDateTime jpkCreationDate;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private Currency currencyCode;
    private String officeCode;


}
