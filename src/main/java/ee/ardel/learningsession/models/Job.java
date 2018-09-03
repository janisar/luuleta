package ee.ardel.learningsession.models;

import lombok.*;
import org.springframework.data.annotation.Id;


@Getter(AccessLevel.PUBLIC)
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    private String id;

    private String description;
    private String title;
    private Company company;
}
