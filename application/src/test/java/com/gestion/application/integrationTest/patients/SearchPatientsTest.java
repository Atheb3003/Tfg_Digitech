package com.gestion.application.integrationTest.patients;

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
public class SearchPatientsTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private String getBaseUrl() {
    return "http://localhost:" + port;
  }

  @Test
  void buscarPacientesPorTexto() {
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

    // Paso 2: Buscar pacientes con texto "test"
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    HttpEntity<Void> request = new HttpEntity<>(headers);

    String searchTerm = "test";
    ResponseEntity<Map> response =
        restTemplate.exchange(
            getBaseUrl() + "/patients/search/" + searchTerm, HttpMethod.GET, request, Map.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    Map<String, Object> body = response.getBody();
    assertThat(body).isNotNull();
    assertThat(body.get("status")).isEqualTo("success");

    Map<String, Object> data = (Map<String, Object>) body.get("data");
    List<Map<String, Object>> results = (List<Map<String, Object>>) data.get("content");
    assertThat(results).isNotEmpty();

    // Validar estructura de los resultados
    String[] expectedKeys = {
      "name",
      "surname",
      "nif",
      "occupation",
      "country",
      "province",
      "town",
      "direction",
      "email",
      "observations",
      "id_contact",
      "birth_date",
      "telephone_number_1",
      "telephone_number_2",
      "is_visible",
      "id_contact_string"
    };

    for (Map<String, Object> patient : results) {
      for (String key : expectedKeys) {
        assertThat(patient).containsKey(key);
      }
      // Extra: Validar que nombre o apellido contenga "test" (ignorando mayúsculas)
      String name = (String) patient.get("name");
      String surname = (String) patient.get("surname");
      assertThat(name.toLowerCase() + " " + surname.toLowerCase()).contains(searchTerm);
    }
  }
}
