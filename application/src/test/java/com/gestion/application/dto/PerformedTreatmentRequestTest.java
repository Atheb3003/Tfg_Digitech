package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class PerformedTreatmentRequestTest {

  @Test
  void testGettersAndSetters() {
    PerformedTreatmentRequest request = new PerformedTreatmentRequest();

    request.setContactId(1234);
    request.setProductId(5678);
    request.setRevisionId(91011);
    request.setPerformedDate(LocalDate.of(2025, 6, 14));

    assertEquals(1234, request.getContactId());
    assertEquals(5678, request.getProductId());
    assertEquals(91011, request.getRevisionId());
    assertEquals(LocalDate.of(2025, 6, 14), request.getPerformedDate());
  }
}
