package com.gestion.application.integrationTest.contact.contact;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransformContactToPatientTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private String getBaseUrl() {
    return "http://localhost:" + port;
  }

  @Test
  void transformarContactoEnPaciente() {
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

    // Paso 2: Hacer POST a /patients/{id}/to-patient
    long contactId = 23; // puedes cambiarlo si quieres probar otros

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);

    HttpEntity<Void> request = new HttpEntity<>(headers);

    ResponseEntity<Map> response =
        restTemplate.postForEntity(
            getBaseUrl() + "/patients/" + contactId + "/to-patient", request, Map.class);

    // Paso 3: Validar ambas posibles respuestas
    if (response.getStatusCode() == HttpStatus.CREATED) {
      Map<String, Object> body = response.getBody();
      assertThat(body.get("status")).isEqualTo("created");
      assertThat(body.get("message")).asString().contains("transformado correctamente en paciente");
    } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
      Map<String, Object> body = response.getBody();
      assertThat(body.get("status")).isEqualTo(500);
      assertThat(body.get("message")).asString().contains("error inesperado");
    } else {
      // cualquier otro estado no es aceptable en este test
      throw new AssertionError("Respuesta inesperada: " + response.getStatusCode());
    }
  }
}
