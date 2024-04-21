package pw.mssql.jpkwb.domain.subject;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import pw.mssql.jpkwb.domain.heading.Heading;

@Entity
@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subject {

    @Id
    private Long id;

    @Embedded
    private Heading heading;

    @Embedded
    private SubjectDetails subjectDetails;

    @Embedded
    private SubjectAddress subjectAddress;

}
