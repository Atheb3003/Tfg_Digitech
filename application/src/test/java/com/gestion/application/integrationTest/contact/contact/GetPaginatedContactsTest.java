package com.gestion.application.integrationTest.contact.contact;

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
public class GetPaginatedContactsTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private String getBaseUrl() {
    return "http://localhost:" + port;
  }

  @Test
  void obtenerContactosPaginados() {
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

    // Paso 2: GET /contacts con token
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    HttpEntity<Void> request = new HttpEntity<>(headers);

    ResponseEntity<Map> response =
        restTemplate.exchange(getBaseUrl() + "/contacts", HttpMethod.GET, request, Map.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    Map<String, Object> body = response.getBody();
    assertThat(body).isNotNull();
    assertThat(body.get("status")).isEqualTo("success");

    Map<String, Object> data = (Map<String, Object>) body.get("data");
    assertThat(data).containsKey("content");

    List<Map<String, Object>> contacts = (List<Map<String, Object>>) data.get("content");
    assertThat(contacts).isNotEmpty();

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

    for (Map<String, Object> contact : contacts) {
      for (String key : expectedKeys) {
        assertThat(contact).containsKey(key);
      }
    }
  }
}
