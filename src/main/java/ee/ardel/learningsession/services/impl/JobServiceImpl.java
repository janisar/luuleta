package ee.ardel.learningsession.services.impl;

import ee.ardel.learningsession.models.Job;
import ee.ardel.learningsession.models.Reaction;
import ee.ardel.learningsession.models.rest.JobRequest;
import ee.ardel.learningsession.models.rest.ReactionType;
import ee.ardel.learningsession.repository.JobRepository;
import ee.ardel.learningsession.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job get(String id) {
        Optional<Job> job = jobRepository.findById(id);

        return job.orElse(null);
    }

    @Override
    public Job create(JobRequest jobRequest) {
        return jobRepository.save(mapToJob(jobRequest));
    }

    @Override
    public Job update(Job job) {
        return jobRepository.save(job);
    }

    private Job mapToJob(JobRequest jobRequest) {

        return Job.builder()
                .companyId(jobRequest.getCompanyId())
                .build();
    }

    @Override
    public Iterable<Job> findAll(String userId) {
        Reaction reaction = new Reaction(userId, ReactionType.UP);
        Reaction reaction2 = new Reaction(userId, ReactionType.DOWN);
        List<Reaction> reactions = Arrays.asList(reaction, reaction2);

        return jobRepository.findByReactionListNotIn(reactions);
    }
}
