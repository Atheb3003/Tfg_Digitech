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
public class DeleteContactByIdTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private String getBaseUrl() {
    return "http://localhost:" + port;
  }

  @Test
  void eliminarContactoPorId() {
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

    // Paso 2: DELETE /contacts/{id}
    long contactId = 10; // cambia según el caso de prueba

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    HttpEntity<Void> request = new HttpEntity<>(headers);

    ResponseEntity<Map> response =
        restTemplate.exchange(
            getBaseUrl() + "/contacts/" + contactId, HttpMethod.DELETE, request, Map.class);

    HttpStatus status = HttpStatus.valueOf(response.getStatusCode().value());

    if (status == HttpStatus.OK) {
      // Caso eliminado correctamente
      Map<String, Object> body = response.getBody();
      assertThat(body.get("status")).isEqualTo("deleted");
      assertThat(body.get("message").toString().toLowerCase()).contains("eliminado");
    } else if (status == HttpStatus.NOT_FOUND) {
      // Caso no encontrado
      Map<String, Object> body = response.getBody();
      assertThat(body.get("status")).isEqualTo(404);
      assertThat(body.get("message").toString().toLowerCase()).contains("no se encontró");
    } else if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
      // Caso error interno
      Map<String, Object> body = response.getBody();
      assertThat(body.get("status")).isEqualTo(500);
      assertThat(body.get("message").toString().toLowerCase()).contains("error inesperado");
    } else {
      throw new AssertionError("Código inesperado: " + status);
    }
  }
}
