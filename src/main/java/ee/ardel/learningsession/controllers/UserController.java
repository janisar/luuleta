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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
    @RequestMapping(value = "/me", method = GET, produces = "application/json")
    public ResponseEntity<User> user() {
        String userId = AuthService.getUserId();
        return new ResponseEntity<>(userService.get(userId), OK);
    }

    @ResponseBody
    @RequestMapping(value = "/me/jobs", method = GET, produces = "application/json")
    public ResponseEntity<Iterable<Job>> jobs() {
        String userId = AuthService.getUserId();
        return new ResponseEntity<>(jobService.findAll(userId), OK);
    }

    @ResponseBody
    @RequestMapping(value = "me/jobs/react", method = POST, produces = "application/json")
    public ResponseEntity<Void> reactToJob(@RequestBody JobReactRequest reactionRequest) {
        userService.reactToJob(reactionRequest, AuthService.getUserId());

        return new ResponseEntity<>(CREATED);
    }
}
