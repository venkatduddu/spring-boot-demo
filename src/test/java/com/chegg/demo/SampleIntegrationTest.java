package com.chegg.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.OK;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@ExtendWith(SpringExtension.class)
public class SampleIntegrationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void testHealth() {
    ResponseEntity<Map> healthCheckResponse = restTemplate
        .exchange("/actuator/health", HttpMethod.GET, null, Map.class);
    assertEquals(OK, healthCheckResponse.getStatusCode());
    assertEquals("UP", healthCheckResponse.getBody().get("status"));
  }
}
