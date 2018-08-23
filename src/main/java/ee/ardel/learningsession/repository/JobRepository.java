package ee.ardel.learningsession.repository;

import ee.ardel.learningsession.models.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<Job, Long> {
}
