package ee.ardel.learningsession.controllers;

import ee.ardel.learningsession.models.Job;
import ee.ardel.learningsession.models.rest.JobRequest;
import ee.ardel.learningsession.services.JobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobControllerTest {

    @Mock
    private JobService jobService;

    @InjectMocks
    private JobController jobController;

    public JobControllerTest() {
    }

    @Test
    public void shouldCreateJobFromRequestObject() {
        String testCompanyId = "Passat Photography";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Authentication authentication = mock(Authentication.class);
        User user = mock(User.class);
        when(authentication.getPrincipal()).thenReturn(user);
        when(user.getUsername()).thenReturn(testCompanyId);


        ArgumentCaptor<JobRequest> jobRequest = ArgumentCaptor.forClass(JobRequest.class);
        when(jobService.create(jobRequest.capture())).thenReturn(new Job());

        jobController.saveJob(new JobRequest(), request);

        JobRequest createdJobRequest = jobRequest.getValue();
        assertEquals(testCompanyId, createdJobRequest.getCompanyId());
    }
}
