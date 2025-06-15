package com.gestion.application.integrationTest.photos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreatePhotoGroupFailureTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private String getBaseUrl() {
    return "http://localhost:" + port;
  }

  @Test
  void errorEsperadoAlCrearGrupoDeFotos() {
    // Paso 1: Login
    HttpHeaders loginHeaders = new HttpHeaders();
    loginHeaders.setContentType(MediaType.APPLICATION_JSON);
    String loginBody =
        """
            {
              "username": "prueba",
              "password": "1234"
            }
        """;

    HttpEntity<String> loginRequest = new HttpEntity<>(loginBody, loginHeaders);
    ResponseEntity<Map> loginResponse =
        restTemplate.postForEntity(getBaseUrl() + "/login", loginRequest, Map.class);

    String token =
        (String) ((Map<String, Object>) loginResponse.getBody().get("data")).get("token");
    assertThat(token).isNotNull();

    // Paso 2: Intentar POST /photos/groups/create-with-photos
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Simulamos una petición inválida o incompleta
    String badRequestBody = "{}";

    HttpEntity<String> request = new HttpEntity<>(badRequestBody, headers);

    ResponseEntity<Map> response =
        restTemplate.exchange(
            getBaseUrl() + "/photos/groups/create-with-photos",
            HttpMethod.POST,
            request,
            Map.class);

    int statusCode = response.getStatusCode().value();
    assertThat(statusCode).isEqualTo(500);

    Map<String, Object> body = response.getBody();
    assertThat(body.get("status")).isEqualTo(500);
    assertThat(body.get("error").toString().toLowerCase()).contains("internal server");
    assertThat(body.get("message").toString().toLowerCase()).contains("error inesperado");
    assertThat(body.get("path")).isEqualTo("/photos/groups/create-with-photos");
  }
}
