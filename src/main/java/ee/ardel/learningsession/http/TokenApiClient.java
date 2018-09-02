package ee.ardel.learningsession.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenApiClient {

    @Value("${tokenapi.base.url}")
    private String baseUrl;
    @Value("${tokenapi.base.port}")
    private String port;

    private RestTemplate rest;
    private HttpHeaders headers;

    public TokenApiClient() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    public String post(String path, Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        HttpEntity<String> requestEntity = new HttpEntity<>(mapper.writeValueAsString(object), headers);
        ResponseEntity<String> responseEntity = rest.exchange(getUrl(path), HttpMethod.POST, requestEntity, String.class);

        return responseEntity.getBody();
    }

    private String getUrl(String path) {
        return baseUrl + ":" + port + "/" + path;
    }
}
