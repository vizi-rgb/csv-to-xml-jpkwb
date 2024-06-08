package pw.mssql.jpkwb.temp.heading;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
