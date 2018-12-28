package ee.ardel.learningsession.models.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TokenRequest {

    private String token;

    public TokenRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
