package ee.ardel.learningsession.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

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
    private Set<String> interestedJobs;
    private Set<String> notInterestedJobs;

    @Getter(AccessLevel.PRIVATE)
    private String password;

    public void generateId() {
        this.id = ObjectId.get().toString();
    }

    public Set<String> getInterestedJobs() {
        if (this.interestedJobs == null) {
            this.interestedJobs = new HashSet<>();
        }
        return this.interestedJobs;
    }

    public Set<String> getNotInterestedJobs() {
        if (this.notInterestedJobs == null) {
            this.notInterestedJobs = new HashSet<>();
        }
        return this.notInterestedJobs;
    }
}
