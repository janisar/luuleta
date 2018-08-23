package ee.ardel.learningsession.models;

import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter
public class Company {

    @Id
    private long id;

    private String name;
    private String address;
    private String description;
    private String image;
}
