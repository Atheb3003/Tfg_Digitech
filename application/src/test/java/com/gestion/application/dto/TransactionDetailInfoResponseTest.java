package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class TransactionDetailInfoResponseTest {

  @Test
  void testGettersAndSetters() {
    TransactionDetailInfoResponse response = new TransactionDetailInfoResponse();

    response.setIdDetail(1001);
    response.setType("PRODUCT");

    response.setProductName("Producto A");
    response.setQuantity(5);
    response.setPrice(new BigDecimal("150.00"));

    response.setProtocolFinished(true);
    response.setProtocolId(500);

    response.setSurgicalTechnique("FUE");
    response.setEstimatedDate(LocalDate.of(2025, 6, 14));
    response.setFollicularUnits(1200);
    response.setSurgeryPrice(new BigDecimal("3000.00"));
    response.setSurgeryDescription("Cirugía capilar");

    assertEquals(1001, response.getIdDetail());
    assertEquals("PRODUCT", response.getType());

    assertEquals("Producto A", response.getProductName());
    assertEquals(5, response.getQuantity());
    assertEquals(new BigDecimal("150.00"), response.getPrice());

    assertTrue(response.getProtocolFinished());
    assertEquals(500, response.getProtocolId());

    assertEquals("FUE", response.getSurgicalTechnique());
    assertEquals(LocalDate.of(2025, 6, 14), response.getEstimatedDate());
    assertEquals(1200, response.getFollicularUnits());
    assertEquals(new BigDecimal("3000.00"), response.getSurgeryPrice());
    assertEquals("Cirugía capilar", response.getSurgeryDescription());
  }
}
