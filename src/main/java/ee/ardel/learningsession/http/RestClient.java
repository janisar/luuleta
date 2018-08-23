package ee.ardel.learningsession.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.ardel.learningsession.form.UserForm;
import ee.ardel.learningsession.models.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClient {

    private String server = "http://tokenapi:8080";
    private RestTemplate rest;
    private HttpHeaders headers;

    public RestClient() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    public String post(String uri, User userForm) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        HttpEntity<String> requestEntity = new HttpEntity<>(mapper.writeValueAsString(userForm), headers);
        ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.POST, requestEntity, String.class);
        return responseEntity.getBody();
    }
}
