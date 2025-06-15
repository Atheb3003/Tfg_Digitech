package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class GroupPhotosResponseTest {

  @Test
  void testGettersAndSetters() {
    GroupPhotosResponse response = new GroupPhotosResponse();

    response.setGroupId(555);
    response.setTitle("Fotos preoperatorias");
    response.setDescription("Fotos tomadas antes de la cirugía");
    response.setCreationDate(LocalDate.of(2025, 6, 14));

    response.setContactId(101);
    response.setConsultationId(202);
    response.setRevisionId(303);

    List<String> photos = Arrays.asList("photo1.jpg", "photo2.jpg", "photo3.jpg");
    response.setPhotos(photos);

    assertEquals(555, response.getGroupId());
    assertEquals("Fotos preoperatorias", response.getTitle());
    assertEquals("Fotos tomadas antes de la cirugía", response.getDescription());
    assertEquals(LocalDate.of(2025, 6, 14), response.getCreationDate());

    assertEquals(101, response.getContactId());
    assertEquals(202, response.getConsultationId());
    assertEquals(303, response.getRevisionId());

    assertEquals(photos, response.getPhotos());
  }
}
