package ee.ardel.learningsession.controllers;

import ee.ardel.learningsession.models.Job;
import ee.ardel.learningsession.models.rest.JobRequest;
import ee.ardel.learningsession.services.JobService;
import ee.ardel.learningsession.services.TokenProvider;
import ee.ardel.learningsession.services.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static ee.ardel.learningsession.util.TokenUtil.resolveToken;
import static org.springframework.http.HttpStatus.OK;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;
    private final TokenProvider tokenProvider;

    @Autowired
    public JobController(JobService jobService, TokenProvider tokenProvider) {
        this.jobService = jobService;
        this.tokenProvider = tokenProvider;
    }

    @ResponseBody
    @GetMapping
    public ResponseEntity<Iterable<Job>> getAll() {
        String userId = AuthService.getUserId();

        return new ResponseEntity<>(jobService.findAll(userId), OK);
    }

    @ResponseBody
    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    public Job saveJob(@RequestBody JobRequest jobRequest, HttpServletRequest request) {
        Authentication a = tokenProvider.getAuthentication(resolveToken(request));
        User user = (User) a.getPrincipal();
        jobRequest.setCompanyId(user.getUsername());
        return jobService.create(jobRequest);
    }
}
