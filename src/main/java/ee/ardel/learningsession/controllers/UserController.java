package ee.ardel.learningsession.controllers;

import ee.ardel.learningsession.models.User;
import ee.ardel.learningsession.models.rest.JobReactRequest;
import ee.ardel.learningsession.services.UserService;
import ee.ardel.learningsession.services.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = GET, produces = "application/json")
    public ResponseEntity<User> user(@PathVariable("id") String id) {
        return new ResponseEntity<>(userService.get(id), OK);
    }

    @ResponseBody
    @RequestMapping(value = "/react", method = POST, produces = "application/json")
    public ResponseEntity<Void> reactToJob(@RequestBody JobReactRequest reactionRequest) {
        userService.reactToJob(reactionRequest, AuthService.getUserId());

        return new ResponseEntity<>(CREATED);
    }
}
