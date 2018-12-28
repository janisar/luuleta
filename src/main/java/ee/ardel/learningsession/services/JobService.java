package ee.ardel.learningsession.services;

import ee.ardel.learningsession.models.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {

    Job get(String id);

    List<Job> findAllByCompany(String companyId);

    Job save(Job job);

    Iterable<Job> findAll(String userId);
}
