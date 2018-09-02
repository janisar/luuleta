package ee.ardel.learningsession.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.ardel.learningsession.models.rest.JobReactRequest;
import ee.ardel.learningsession.models.rest.ReactionType;
import ee.ardel.learningsession.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
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
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    public void reactToJob() throws Exception {
        JobReactRequest jobReactRequest = new JobReactRequest("id", ReactionType.UP);

        MockHttpServletResponse response = mvc.perform(
                post("/user/react").contentType(APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(jobReactRequest)))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        assertThat(response.getContentAsString()).isEqualTo("ok");
    }
}