package com.gestion.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.gestion.application.dto.ProtocolTreatmentRequest;
import com.gestion.application.model.ProtocolTreatment;
import org.junit.jupiter.api.Test;

public class ProtocolTreatmentMapperTest {

  private final ProtocolTreatmentMapper mapper = new ProtocolTreatmentMapper();

  @Test
  void testToEntity_WithAllFields() {
    ProtocolTreatmentRequest request = new ProtocolTreatmentRequest();
    request.setProtocolId(10);
    request.setProductId(20);
    request.setIsFinished(true);
    request.setIsPaid(true);

    ProtocolTreatment entity = mapper.toEntity(request);

    assertNotNull(entity.getProtocol());
    assertEquals(10, entity.getProtocol().getIdProtocol());

    assertNotNull(entity.getProduct());
    assertEquals(20, entity.getProduct().getIdProduct());

    assertTrue(entity.getIsFinished());
    assertTrue(entity.getIsPaid());
  }

  @Test
  void testToEntity_WithNullBooleans() {
    ProtocolTreatmentRequest request = new ProtocolTreatmentRequest();
    request.setProtocolId(null);
    request.setProductId(null);
    request.setIsFinished(null);
    request.setIsPaid(null);

    ProtocolTreatment entity = mapper.toEntity(request);

    assertNull(entity.getProtocol());
    assertNull(entity.getProduct());
    assertFalse(entity.getIsFinished()); // Default false
    assertFalse(entity.getIsPaid()); // Default false
  }
}
