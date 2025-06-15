package com.gestion.application.integrationTest.photos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetPhotoGroupsByContactTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private String getBaseUrl() {
    return "http://localhost:" + port;
  }

  @Test
  void obtenerGruposFotosPorContacto_existente_o_no() {
    // Login
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

    // Probar con contacto existente o no existente
    long contactId = 1; // cámbialo por otro como 10 si quieres probar el caso 404

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);

    HttpEntity<Void> request = new HttpEntity<>(headers);
    ResponseEntity<Map> response =
        restTemplate.exchange(
            getBaseUrl() + "/photos/groups/contact/" + contactId,
            HttpMethod.GET,
            request,
            Map.class);

    if (response.getStatusCode() == HttpStatus.OK) {
      Map<String, Object> body = response.getBody();
      assertThat(body.get("status")).isEqualTo("success");
      List<Map<String, Object>> data = (List<Map<String, Object>>) body.get("data");
      assertThat(data).isNotNull();
      // Validación simple: al menos cada grupo tiene groupId y photos
      for (Map<String, Object> group : data) {
        assertThat(group).containsKeys("groupId", "photos");
        List<String> photos = (List<String>) group.get("photos");
        assertThat(photos).isNotNull();
      }
    } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
      Map<String, Object> body = response.getBody();
      assertThat(body.get("status")).isEqualTo(404);
      assertThat(body.get("error").toString()).contains("Not Found");
      assertThat(body.get("message").toString()).contains("No se encontró un contacto");
      assertThat(body.get("path").toString()).endsWith("/photos/groups/contact/" + contactId);
    } else {
      throw new AssertionError("Respuesta inesperada: " + response.getStatusCode());
    }
  }
}
