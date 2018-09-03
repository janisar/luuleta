package ee.ardel.learningsession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

public class SecurityMock {

    public static Authentication getAuth() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) () -> "ROLE_USER");

        User tempUser = new User("TEST", "TEST", true, true, true, true, authorities);

        return new UsernamePasswordAuthenticationToken(tempUser, "TEST", authorities);
    }
}
