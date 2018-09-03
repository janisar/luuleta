package ee.ardel.learningsession.services;

import ee.ardel.learningsession.models.User;
import ee.ardel.learningsession.models.rest.JobReactRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User get(String id);

    void reactToJob(JobReactRequest jobReactRequest, String userId);
}
