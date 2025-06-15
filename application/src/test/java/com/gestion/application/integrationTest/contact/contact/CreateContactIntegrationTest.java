// package com.gestion.application.integrationTest.contact.contact;
//
// import static org.assertj.core.api.Assertions.assertThat;
//
// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.util.Map;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.boot.test.web.server.LocalServerPort; // ✅ Correcto en Spring Boot 3+
// import org.springframework.http.*;
//
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// public class CreateContactIntegrationTest {
//
//  @LocalServerPort private int port;
//
//  @Autowired private TestRestTemplate restTemplate;
//
//  private final ObjectMapper objectMapper = new ObjectMapper();
//
//  private String getBaseUrl() {
//    return "http://localhost:" + port;
//  }
//
//  private String readJson(String filename) throws IOException {
//    return Files.readString(
//        Paths.get(
//            "src/test/java/com/gestion/application/integrationTest/contact/createContact/"
//                + filename));
//  }
//
//  @Test
//  void crearContactoDespuesDeLogin() throws Exception {
//    // LOGIN
//    HttpHeaders loginHeaders = new HttpHeaders();
//    loginHeaders.setContentType(MediaType.APPLICATION_JSON);
//    String loginBody =
//        """
//            {
//                "username": "prueba",
//                "password": "1234"
//            }
//        """;
//
//    HttpEntity<String> loginRequest = new HttpEntity<>(loginBody, loginHeaders);
//    ResponseEntity<Map> loginResponse =
//        restTemplate.postForEntity(getBaseUrl() + "/login", loginRequest, Map.class);
//    String token = (String) ((Map<?, ?>) loginResponse.getBody().get("data")).get("token");
//    assertThat(token).isNotNull();
//
//    // POST CONTACT
//    HttpHeaders headers = new HttpHeaders();
//    headers.setContentType(MediaType.APPLICATION_JSON);
//    headers.setBearerAuth(token);
//
//    String requestBody = readJson("Request.json");
//
//    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
//    ResponseEntity<String> response =
//        restTemplate.postForEntity(getBaseUrl() + "/contacts", request, String.class);
//
//    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//
//    // VALIDACIÓN DE RESPUESTA
//    JsonNode actualResponse = objectMapper.readTree(response.getBody());
//    JsonNode expectedResponse = objectMapper.readTree(readJson("Response.json"));
//
//    // Ignorar campos dinámicos
//    JsonNode actualData = actualResponse.get("data");
//    JsonNode expectedData = expectedResponse.get("data");
//
//    assertThat(actualResponse.get("status").asText()).isEqualTo("created");
//    assertThat(actualData.get("name").asText()).isEqualTo(expectedData.get("name").asText());
//
// assertThat(actualData.get("surname").asText()).isEqualTo(expectedData.get("surname").asText());
//    assertThat(actualData.get("email").asText()).contains("@");
//
//    assertThat(actualData.get("id_contact")).isNotNull();
//    assertThat(actualData.get("id_contact_string")).isNotNull();
//  }
// }
