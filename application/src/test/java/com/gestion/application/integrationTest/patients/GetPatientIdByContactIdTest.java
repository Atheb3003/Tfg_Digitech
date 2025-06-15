package com.gestion.application.integrationTest.patients;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetPatientIdByContactIdTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private String getBaseUrl() {
    return "http://localhost:" + port;
  }

  @Test
  void obtenerIdPacientePorIdContacto() {
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

    // Paso 2: GET /patients/by-contact/{id}
    long contactId = 27; // puedes probar con uno que exista o no

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    HttpEntity<Void> request = new HttpEntity<>(headers);

    ResponseEntity<Map> response =
        restTemplate.exchange(
            getBaseUrl() + "/patients/by-contact/" + contactId, HttpMethod.GET, request, Map.class);

    if (response.getStatusCode() == HttpStatus.OK) {
      // Paciente encontrado
      Map<String, Object> body = response.getBody();
      assertThat(body.get("status")).isEqualTo("found");
      assertThat(body.get("data")).isInstanceOf(Number.class);
    } else if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
      // Paciente no encontrado
      assertThat(response.getBody()).isNull();
    } else {
      throw new AssertionError("Código inesperado: " + response.getStatusCode());
    }
  }
}
