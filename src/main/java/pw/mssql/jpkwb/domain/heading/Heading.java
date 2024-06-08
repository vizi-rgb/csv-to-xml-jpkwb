package pw.mssql.jpkwb.domain.heading;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

@Embeddable
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Heading {

    @Embedded
    private FormCode formCode;
    private Short formVariant;
    private String submissionPurpose;
    private LocalDateTime jpkCreationDate;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Currency currencyCode;
    private String officeCode;


}
