package ee.ardel.learningsession.controllers;

import ee.ardel.learningsession.models.User;
import ee.ardel.learningsession.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = GET, produces = "application/json")
    public User user(@PathVariable("id") String id) {
        return userRepository.findById(id).orElse(null);
    }
}
