package ee.ardel.learningsession.models;

import lombok.*;
import org.springframework.data.annotation.Id;


@Getter(AccessLevel.PUBLIC)
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    private long id;

    private String description;
    private String title;
    private Company company;
}
