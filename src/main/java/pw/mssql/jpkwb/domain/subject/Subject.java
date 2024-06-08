package pw.mssql.jpkwb.domain.subject;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private SubjectDetails subjectDetails;

    @Embedded
    private SubjectAddress subjectAddress;

}
