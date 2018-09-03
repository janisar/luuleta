package ee.ardel.learningsession.services.impl;


import ee.ardel.learningsession.models.Job;
import ee.ardel.learningsession.models.User;
import ee.ardel.learningsession.models.rest.JobReactRequest;
import ee.ardel.learningsession.models.rest.ReactionType;
import ee.ardel.learningsession.repository.UserRepository;
import ee.ardel.learningsession.services.JobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private JobService jobService;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void shouldUpdateUserWithReactedJob() {
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        JobReactRequest reactRequest = new JobReactRequest("test1", ReactionType.UP);
        User testUser = User.builder().firstName("Test").build();
        Job job = Job.builder().description("Developer").build();

        when(userRepository.findById("TEST")).thenReturn(Optional.of(testUser));
        when(jobService.get("test1")).thenReturn(job);

        userService.reactToJob(reactRequest, "TEST");

        verify(jobService).get(eq("test1"));
        verify(userRepository).save(argumentCaptor.capture());

        assertEquals(1, argumentCaptor.getValue().getInterestedJobs().size());
    }

    @Test
    public void shouldUpdateUserWithNotInterestedJob() {
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        JobReactRequest reactRequest = new JobReactRequest("test1", ReactionType.DOWN);
        User testUser = User.builder().firstName("Test").build();
        Job job = Job.builder().description("Developer").build();

        when(userRepository.findById("TEST")).thenReturn(Optional.of(testUser));
        when(jobService.get("test1")).thenReturn(job);

        userService.reactToJob(reactRequest, "TEST");

        verify(jobService).get(eq("test1"));
        verify(userRepository).save(argumentCaptor.capture());

        assertEquals(1, argumentCaptor.getValue().getNotInterestedJobs().size());
    }
}