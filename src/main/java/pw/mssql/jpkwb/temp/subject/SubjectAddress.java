package pw.mssql.jpkwb.temp.subject;

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
public class SubjectAddress {

    private String countryCode;
    private String region;
    private String county;
    private String community;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String city;
    private String postalCode;
    private String postOfficeCity;
}
