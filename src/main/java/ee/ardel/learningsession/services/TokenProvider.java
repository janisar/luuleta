package ee.ardel.learningsession.services;

import org.springframework.security.core.Authentication;

public interface TokenProvider {

    Authentication getAuthentication(String jwt);
}
