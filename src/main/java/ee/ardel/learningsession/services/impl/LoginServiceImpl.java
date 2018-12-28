package ee.ardel.learningsession.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import ee.ardel.learningsession.form.UserForm;
import ee.ardel.learningsession.http.TokenApiClient;
import ee.ardel.learningsession.models.Role;
import ee.ardel.learningsession.models.User;
import ee.ardel.learningsession.repository.UserRepository;
import ee.ardel.learningsession.services.LoginService;
import ee.ardel.learningsession.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginServiceImpl implements LoginService {

    private final TokenApiClient tokenApiClient;

    private final UserRepository userRepository;

    @Value("${password.hash}")
    private String hash;

    @Autowired
    public LoginServiceImpl(TokenApiClient tokenApiClient, UserRepository userRepository) {
        this.tokenApiClient = tokenApiClient;
        this.userRepository = userRepository;
    }

    @Override
    public String login(UserForm userForm) throws JsonProcessingException {
        User user = User.builder()
                .email(userForm.getEmail())
                .password(PasswordUtil.hashPassword(userForm.getPassword().toCharArray(), hash.getBytes()))
                .build();

        Optional<User> loginUser = userRepository.findOne(Example.of(user, ExampleMatcher.matchingAll()));

        if (loginUser.isPresent()) {
            return tokenApiClient.post("/token", loginUser.get());
        }
        throw new SecurityException("unauthorized");
    }

    @Override
    public void register(UserForm userForm) {
        User user = User.builder()
                .email(userForm.getEmail())
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .role(Role.from(userForm.getRole()))
                .password(PasswordUtil.hashPassword(userForm.getPassword().toCharArray(), hash.getBytes()))
                .build();
        user.generateId();

        userRepository.save(user);
    }
}
