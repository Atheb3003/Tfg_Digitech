package com.gestion.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.gestion.application.dto.TransactionDetailRequest;
import com.gestion.application.dto.TransactionDetailResponse;
import com.gestion.application.model.Product;
import com.gestion.application.model.ProtocolTreatment;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.model.Transaction;
import com.gestion.application.model.TransactionDetail;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class TransactionDetailMapperTest {

  private final TransactionDetailMapper mapper = new TransactionDetailMapper();

  @Test
  void testToEntity() {
    TransactionDetailRequest request = new TransactionDetailRequest();
    request.setTransactionId(1);
    request.setProductId(2);
    request.setQuantity(3);
    request.setPrice(new BigDecimal("150.00"));
    request.setProtocolTreatmentId(4);
    request.setSurgeryReservationId(5);

    TransactionDetail entity = mapper.toEntity(request);

    assertNotNull(entity.getTransaction());
    assertEquals(1, entity.getTransaction().getIdTransaction());

    assertNotNull(entity.getProduct());
    assertEquals(2, entity.getProduct().getIdProduct());

    assertEquals(3, entity.getQuantity());
    assertEquals(new BigDecimal("150.00"), entity.getPrice());

    assertNotNull(entity.getProtocolTreatment());
    assertEquals(4, entity.getProtocolTreatment().getId());

    assertNotNull(entity.getSurgeryReservation());
    assertEquals(5, entity.getSurgeryReservation().getIdSurgeryReservation());

    assertTrue(entity.getIsVisible());
  }

  @Test
  void testToResponse() {
    Transaction transaction = new Transaction();
    transaction.setIdTransaction(1);

    Product product = new Product();
    product.setIdProduct(2);

    ProtocolTreatment protocolTreatment = new ProtocolTreatment();
    protocolTreatment.setId(4);

    SurgeryReservation surgeryReservation = new SurgeryReservation();
    surgeryReservation.setIdSurgeryReservation(5);

    TransactionDetail entity = new TransactionDetail();
    entity.setIdDetail(10);
    entity.setTransaction(transaction);
    entity.setProduct(product);
    entity.setQuantity(3);
    entity.setPrice(new BigDecimal("150.00"));
    entity.setProtocolTreatment(protocolTreatment);
    entity.setSurgeryReservation(surgeryReservation);

    TransactionDetailResponse response = mapper.toResponse(entity);

    assertEquals(10, response.getIdDetail());
    assertEquals(1, response.getTransactionId());
    assertEquals(2, response.getProductId());
    assertEquals(3, response.getQuantity());
    assertEquals(new BigDecimal("150.00"), response.getPrice());
    assertEquals(4, response.getProtocolTreatmentId());
    assertEquals(5, response.getSurgeryReservationId());
  }
}
