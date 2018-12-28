package ee.ardel.learningsession.controllers;

import ee.ardel.learningsession.models.Job;
import ee.ardel.learningsession.models.rest.JobFilterRequest;
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
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
    @RequestMapping(method = GET)
    public ResponseEntity<Iterable<Job>> getAll() {
        String userId = AuthService.getUserId();

        return new ResponseEntity<>(jobService.findAll(userId), OK);
    }

    @ResponseBody
    @RequestMapping(method = POST)
    public Job saveJob(@RequestBody Job job, HttpServletRequest request) {
        Authentication a = tokenProvider.getAuthentication(resolveToken(request));
        User user = (User) a.getPrincipal();
        job.setCompanyId(user.getUsername());
        return jobService.save(job);
    }
}
