package pw.mssql.jpkwb.temp.subject;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TempSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private SubjectDetails subjectDetails;

    @Embedded
    private SubjectAddress subjectAddress;

}
