package ee.ardel.learningsession.services.impl;

import ee.ardel.learningsession.models.Job;
import ee.ardel.learningsession.models.Location;
import ee.ardel.learningsession.repository.JobRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
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

    @Test
    public void shouldSaveJobWithLocation() {
        Job testJob = new Job();
        Location location = new Location();
        location.setLat(1.1);
        location.setLng(101.22);
        testJob.setLocation(location);


        ArgumentCaptor<Job> jobArgumentCaptor = ArgumentCaptor.forClass(Job.class);

        when(jobRepository.save(jobArgumentCaptor.capture())).thenReturn(new Job());

        jobService.update(testJob);

        assertNotNull(jobArgumentCaptor.getValue().getLocation());
    }
}