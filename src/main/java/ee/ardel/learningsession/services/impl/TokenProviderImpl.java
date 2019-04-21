package ee.ardel.learningsession.services.impl;

import ee.ardel.learningsession.services.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

@Component
public class TokenProviderImpl implements TokenProvider {

    private final String publicKey;

    public TokenProviderImpl(@Value("${jwt.public.key}") String publicKey) {

        this.publicKey = publicKey;
    }

    @Override
    public Authentication getAuthentication(String jwtString) {

        var jwt = Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwtString).getBody();
        List<GrantedAuthority> authorities = getGrantedAuthorities(jwt.get("role"));


        return new UsernamePasswordAuthenticationToken(
                new User(
                        jwt.get(Claims.ID).toString(), "",
                        true, true,
                        true, true, authorities),
                "", authorities);
    }

    private List<GrantedAuthority> getGrantedAuthorities(Object role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) () -> "ROLE_USER");
        if (role != null) {
            authorities.add((GrantedAuthority) role::toString);
        }

        return authorities;
    }

    private Key getPublicKey() {
        try {
            byte[] encoded = Base64.decodeBase64(publicKey);
            var kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(new X509EncodedKeySpec(encoded));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Kaiki on putsiss");
        }
    }
}
