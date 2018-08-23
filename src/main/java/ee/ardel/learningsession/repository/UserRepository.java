package ee.ardel.learningsession.repository;

import ee.ardel.learningsession.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
