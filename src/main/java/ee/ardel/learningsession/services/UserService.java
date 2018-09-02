package ee.ardel.learningsession.services;

import ee.ardel.learningsession.models.rest.JobReactRequest;

public interface UserService {

    void reactToJob(JobReactRequest jobReactRequest);
}
