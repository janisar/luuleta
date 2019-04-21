package ee.ardel.learningsession.services.impl;

import ee.ardel.learningsession.models.Job;
import ee.ardel.learningsession.models.Reaction;
import ee.ardel.learningsession.models.User;
import ee.ardel.learningsession.models.rest.JobReactRequest;
import ee.ardel.learningsession.repository.UserRepository;
import ee.ardel.learningsession.services.JobService;
import ee.ardel.learningsession.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static ee.ardel.learningsession.models.rest.ReactionType.UP;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JobService jobService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JobService jobService) {
        this.userRepository = userRepository;
        this.jobService = jobService;
    }

    @Override
    public User get(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void reactToJob(JobReactRequest jobReactRequest, String userId) {
        User user = get(userId);
        if (user != null) {
            Job job = jobService.get(jobReactRequest.getId());
            if (job != null && job.getReactionList() == null) {
                job.setReactionList(new ArrayList<>());
            }
            if (job != null) {
                job.getReactionList().add(new Reaction(userId, jobReactRequest.getReactionType()));
                if (UP.equals(jobReactRequest.getReactionType())) {
                    user.getInterestedJobs().add(job.getId());
                } else {
                    user.getNotInterestedJobs().add(job.getId());
                }
                userRepository.save(user);
                jobService.update(job);
            }
        }
    }
}
