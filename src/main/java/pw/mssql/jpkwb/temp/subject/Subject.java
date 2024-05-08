package pw.mssql.jpkwb.temp.subject;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subject {

    @Id
    private Long id;

    @Embedded
    private SubjectDetails subjectDetails;

    @Embedded
    private SubjectAddress subjectAddress;

}
