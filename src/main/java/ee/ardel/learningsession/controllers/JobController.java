package ee.ardel.learningsession.controllers;

import ee.ardel.learningsession.models.Job;
import ee.ardel.learningsession.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/job")
public class JobController {

    private final JobRepository jobRepository;

    @Autowired
    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @ResponseBody
    @RequestMapping(method = GET)
    public Iterable<Job> getAll() {
        return jobRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(method = POST)
    public Job saveJob(@RequestBody Job job) {
        return jobRepository.save(job);
    }
}
