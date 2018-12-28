package ee.ardel.learningsession.services.impl;

import ee.ardel.learningsession.models.Job;
import ee.ardel.learningsession.models.rest.JobFilterRequest;
import ee.ardel.learningsession.repository.JobRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobServiceImplTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobServiceImpl jobService;

    @Test
    public void shouldGetAllJobs() {
        jobService.findAll("");
    }

    @Test
    public void shouldGetJob() {
        String jobId = "test1";
        Job testJob = new Job();

        when(jobRepository.findById(jobId)).thenReturn(Optional.of(testJob));

        Job job = jobService.get(jobId);

        assertNotNull(job);
    }
}