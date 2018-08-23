package ee.ardel.learningsession.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User {

    @Id
    private String id;

    private String email;
    private String firstName;
    private String lastName;
    private Role role;

    @Getter(AccessLevel.PRIVATE)
    private String password;

    public void generateId() {
        this.id = ObjectId.get().toString();
    }
}
