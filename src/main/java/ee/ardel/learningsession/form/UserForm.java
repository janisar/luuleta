package ee.ardel.learningsession.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private int role;
}
