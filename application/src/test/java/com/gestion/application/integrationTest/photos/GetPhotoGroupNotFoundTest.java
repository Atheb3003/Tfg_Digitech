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
public class GetPhotoGroupNotFoundTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private String getBaseUrl() {
    return "http://localhost:" + port;
  }

  @Test
  void obtenerGrupoFotosInexistente() {
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

    // Paso 2: GET /photos/groups/99 (no existe)
    long groupId = 99;

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);

    HttpEntity<Void> request = new HttpEntity<>(headers);

    ResponseEntity<Map> response =
        restTemplate.exchange(
            getBaseUrl() + "/photos/groups/" + groupId, HttpMethod.GET, request, Map.class);

    int statusCode = response.getStatusCode().value();
    assertThat(statusCode).isEqualTo(404);

    Map<String, Object> body = response.getBody();
    assertThat(body.get("status")).isEqualTo(404);
    assertThat(body.get("error").toString()).contains("Not Found");
    assertThat(body.get("message").toString().toLowerCase()).contains("no se encontró");
    assertThat(body.get("path").toString()).endsWith("/photos/groups/" + groupId);
  }
}
