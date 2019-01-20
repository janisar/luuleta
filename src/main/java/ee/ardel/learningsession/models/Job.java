package ee.ardel.learningsession.models;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

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
    private String salary;
    private String companyId;
    private Location location;

    List<Reaction> reactionList;
}
