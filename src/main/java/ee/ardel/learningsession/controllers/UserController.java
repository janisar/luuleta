package ee.ardel.learningsession.controllers;

import ee.ardel.learningsession.models.Job;
import ee.ardel.learningsession.models.User;
import ee.ardel.learningsession.models.rest.JobReactRequest;
import ee.ardel.learningsession.services.JobService;
import ee.ardel.learningsession.services.UserService;
import ee.ardel.learningsession.services.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;
    private final JobService jobService;

    @Autowired
    public UserController(UserService userService, JobService jobService) {
        this.userService = userService;
        this.jobService = jobService;
    }

    @ResponseBody
    @GetMapping(value = "/me", produces = "application/json")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<User> user() {
        String userId = AuthService.getUserId();

        return new ResponseEntity<>(userService.get(userId), OK);
    }

    @ResponseBody
    @GetMapping(value = "/me/jobs", produces = "application/json")
    public ResponseEntity<Iterable<Job>> jobs() {
        String userId = AuthService.getUserId();
        return new ResponseEntity<>(jobService.findAll(userId), OK);
    }

    @ResponseBody
    @GetMapping(value = "me/jobs/react", produces = "application/json")
    public ResponseEntity<Void> reactToJob(@RequestBody JobReactRequest reactionRequest) {
        userService.reactToJob(reactionRequest, AuthService.getUserId());

        return new ResponseEntity<>(CREATED);
    }
}
