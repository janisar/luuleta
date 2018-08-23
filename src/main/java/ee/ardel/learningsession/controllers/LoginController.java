package ee.ardel.learningsession.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import ee.ardel.learningsession.form.UserForm;
import ee.ardel.learningsession.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<String> login(@RequestBody UserForm user) {
        try {
            String token = loginService.login(user);
            return new ResponseEntity<>(token, OK);
        } catch (SecurityException e) {
            return new ResponseEntity<>(UNAUTHORIZED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/register", method = POST)
    public ResponseEntity<Void> register(@RequestBody UserForm user) {
        loginService.register(user);
        return new ResponseEntity<>(CREATED);
    }
}
