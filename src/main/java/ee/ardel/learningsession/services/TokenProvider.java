package ee.ardel.learningsession.services;

import org.springframework.security.core.Authentication;

public interface TokenProvider {

    String generateToken();

    boolean validateToken(String jwt);

    Authentication getAuthentication(String jwt);
}
