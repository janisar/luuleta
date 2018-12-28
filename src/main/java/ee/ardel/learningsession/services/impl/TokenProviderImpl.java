package ee.ardel.learningsession.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import ee.ardel.learningsession.http.TokenApiClient;
import ee.ardel.learningsession.models.rest.TokenRequest;
import ee.ardel.learningsession.services.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TokenProviderImpl implements TokenProvider {

    private final TokenApiClient tokenApiClient;

    @Autowired
    public TokenProviderImpl(TokenApiClient tokenApiClient) {
        this.tokenApiClient = tokenApiClient;
    }

    @Override
    public String generateToken() {
        return null;
    }

    @Override
    public boolean validateToken(String jwt) {
        try {
            return validate(jwt);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Authentication getAuthentication(String jwt) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) () -> "ROLE_USER");

        try {
            String user = tokenApiClient.post("token/user", new TokenRequest(jwt));
            User tempUser = new User(user, "", true, true, true, true, authorities);

            return new UsernamePasswordAuthenticationToken(tempUser, "", authorities);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new AnonymousAuthenticationToken("", null, null);
    }

    private boolean validate(String token) throws JsonProcessingException {
        String validateTokenPath = "token/validate";
        String response = tokenApiClient.post(validateTokenPath, new TokenRequest(token));

        return Boolean.parseBoolean(response);
    }
}
