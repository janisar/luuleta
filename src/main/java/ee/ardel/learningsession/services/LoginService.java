package ee.ardel.learningsession.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import ee.ardel.learningsession.form.UserForm;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    String login(UserForm userForm) throws SecurityException, JsonProcessingException;

    void register(UserForm userForm);
}
