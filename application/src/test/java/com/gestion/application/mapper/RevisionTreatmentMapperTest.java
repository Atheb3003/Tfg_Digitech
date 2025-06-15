package com.gestion.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.gestion.application.dto.RevisionTreatmentRequest;
import com.gestion.application.dto.RevisionTreatmentResponse;
import com.gestion.application.model.Product;
import com.gestion.application.model.Revision;
import com.gestion.application.model.RevisionTreatment;
import org.junit.jupiter.api.Test;

public class RevisionTreatmentMapperTest {

  private final RevisionTreatmentMapper mapper = new RevisionTreatmentMapper();

  @Test
  void testToEntity() {
    RevisionTreatmentRequest request = new RevisionTreatmentRequest();
    request.setRevisionId(10);
    request.setProductId(20);

    RevisionTreatment entity = mapper.toEntity(request);

    assertNotNull(entity.getRevision());
    assertEquals(10, entity.getRevision().getIdRevision());

    assertNotNull(entity.getProduct());
    assertEquals(20, entity.getProduct().getIdProduct());
  }

  @Test
  void testToResponse() {
    Revision revision = new Revision();
    revision.setIdRevision(15);

    Product product = new Product();
    product.setIdProduct(25);

    RevisionTreatment entity = new RevisionTreatment();
    entity.setId(1);
    entity.setRevision(revision);
    entity.setProduct(product);

    RevisionTreatmentResponse response = mapper.toResponse(entity);

    assertEquals(1, response.getId());
    assertEquals(15, response.getRevisionId());
    assertEquals(25, response.getProductId());
  }
}
