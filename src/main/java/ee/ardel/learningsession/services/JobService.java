package ee.ardel.learningsession.services;

import ee.ardel.learningsession.models.Job;
import ee.ardel.learningsession.models.rest.JobRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {

    Job get(String id);

    Job create(JobRequest jobRequest);

    Job update(Job jobRequest);

    Iterable<Job> findAll(String userId);
}
