package ee.ardel.learningsession.controllers;

import ee.ardel.learningsession.models.User;
import ee.ardel.learningsession.models.rest.JobReactRequest;
import ee.ardel.learningsession.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping(value = "/{id}", method = GET, produces = "application/json")
    public User user(@PathVariable("id") String id) {
        return userRepository.findById(id).orElse(null);
    }

    @ResponseBody
    @RequestMapping(value = "/react", method = POST, produces = "application/json")
    public String reactToJob(@RequestBody JobReactRequest reactionRequest, HttpServletRequest request) {
        return "ok";
    }
}
