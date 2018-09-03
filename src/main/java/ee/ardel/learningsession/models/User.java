package ee.ardel.learningsession.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> interestedJobs;
    private List<String> notInterestedJobs;

    @Getter(AccessLevel.PRIVATE)
    private String password;

    public void generateId() {
        this.id = ObjectId.get().toString();
    }

    public List<String> getInterestedJobs() {
        if (this.interestedJobs == null) {
            this.interestedJobs = new ArrayList<>();
        }
        return this.interestedJobs;
    }

    public List<String> getNotInterestedJobs() {
        if (this.notInterestedJobs == null) {
            this.notInterestedJobs = new ArrayList<>();
        }
        return this.notInterestedJobs;
    }
}
