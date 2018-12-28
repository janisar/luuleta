package ee.ardel.learningsession.repository;

import ee.ardel.learningsession.models.Job;
import ee.ardel.learningsession.models.Reaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobRepository extends MongoRepository<Job, String> {

    List<Job> findByCompanyId(String companyId);

    List<Job> findByReactionListNotIn(List<Reaction> reactionList);
}
