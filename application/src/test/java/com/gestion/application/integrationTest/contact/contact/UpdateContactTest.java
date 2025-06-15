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
public class UpdateContactTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private String getBaseUrl() {
    return "http://localhost:" + port;
  }

  @Test
  void actualizarContactoExistente() {
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

    // Paso 2: PUT /contacts/{id}
    long contactId = 1; // cambia según el contacto que quieres actualizar

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    headers.setContentType(MediaType.APPLICATION_JSON);

    String updateBody =
        """
            {
              "name": "Nina Actualizada",
              "surname": "González",
              "nif": "12345678Z",
              "birth_date": "1990-05-10",
              "occupation": "Médica",
              "country": "España",
              "province": "Madrid",
              "town": "Alcobendas",
              "direction": "Calle Nueva 456",
              "telephone_number_1": "+34 600000000",
              "telephone_number_2": "+34 699999999",
              "email": "maria.actualizada@example.com",
              "observations": "Paciente actualizada con nuevo historial.",
              "is_visible": true
            }
        """;

    HttpEntity<String> updateRequest = new HttpEntity<>(updateBody, headers);

    ResponseEntity<Map> response =
        restTemplate.exchange(
            getBaseUrl() + "/contacts/" + contactId, HttpMethod.PUT, updateRequest, Map.class);

    int statusCode = response.getStatusCode().value();
    assertThat(statusCode).isEqualTo(200); // o HttpStatus.OK.value()

    Map<String, Object> body = response.getBody();
    assertThat(body.get("status")).isEqualTo("updated");

    Map<String, Object> data = (Map<String, Object>) body.get("data");
    assertThat(data.get("name")).isEqualTo("Nina Actualizada");
    assertThat(data.get("email")).isEqualTo("maria.actualizada@example.com");
    assertThat(data.get("telephone_number_1")).isEqualTo("+34 600000000");
  }
}
