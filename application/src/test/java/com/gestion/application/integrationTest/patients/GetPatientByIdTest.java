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
public class GetPatientByIdTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private String getBaseUrl() {
    return "http://localhost:" + port;
  }

  @Test
  void buscarPacientePorId() {
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

    // Paso 2: GET /patients/{id}
    long patientId = 5; // Puedes probar con un ID inexistente también (ej: 99)

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    HttpEntity<Void> request = new HttpEntity<>(headers);

    ResponseEntity<Map> response =
        restTemplate.exchange(
            getBaseUrl() + "/patients/" + patientId, HttpMethod.GET, request, Map.class);

    if (response.getStatusCode() == HttpStatus.OK) {
      // Paciente encontrado
      Map<String, Object> body = response.getBody();
      assertThat(body.get("status")).isEqualTo("success");

      Map<String, Object> data = (Map<String, Object>) body.get("data");
      assertThat(data).containsKeys("id_patient", "id_contact", "discharge_date", "is_visible");

    } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
      // Paciente no encontrado
      Map<String, Object> body = response.getBody();
      assertThat(body.get("status")).isEqualTo(404);
      assertThat(body.get("message").toString().toLowerCase()).contains("no se encontró");
    } else {
      throw new AssertionError("Código de respuesta inesperado: " + response.getStatusCode());
    }
  }
}
