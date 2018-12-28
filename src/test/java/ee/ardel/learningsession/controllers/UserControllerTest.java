package ee.ardel.learningsession.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.ardel.learningsession.SecurityMock;
import ee.ardel.learningsession.models.rest.JobReactRequest;
import ee.ardel.learningsession.models.rest.ReactionType;
import ee.ardel.learningsession.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private MockMvc mvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setup() {
        SecurityContextHolder.getContext().setAuthentication(SecurityMock.getAuth());

        JacksonTester.initFields(this, new ObjectMapper());

        mvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    public void shouldReactToJob() throws Exception {
//        JobReactRequest jobReactRequest = new JobReactRequest("id", ReactionType.UP);
//
//        MockHttpServletResponse response = mvc.perform(
//                post("/user/react")
//                        .contentType(APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(jobReactRequest)))
//                .andReturn().getResponse();
//
//        verify(userService, Mockito.times(1)).reactToJob(any(JobReactRequest.class), eq("TEST"));
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @After
    public void tearDown() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}