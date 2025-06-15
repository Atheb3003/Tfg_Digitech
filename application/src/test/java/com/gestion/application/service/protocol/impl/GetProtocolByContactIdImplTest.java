package com.gestion.application.service.protocol.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ProtocolResponse;
import com.gestion.application.dto.ProtocolTreatmentResponse;
import com.gestion.application.exception.ProtocolNotFoundException;
import com.gestion.application.model.*;
import com.gestion.application.repository.ProtocolRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class GetProtocolByContactIdImplTest {

  @Mock private ProtocolRepository protocolRepository;

  @InjectMocks private GetProtocolByContactIdImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnProtocolResponsesForContact() {
    // Arrange
    Integer contactId = 1;

    Product product = new Product();
    product.setIdProduct(100);
    product.setName("Tratamiento 1");

    ProtocolTreatment treatment = new ProtocolTreatment();
    treatment.setId(1);
    treatment.setProduct(product);
    treatment.setPrice(BigDecimal.TEN);
    treatment.setIsFinished(true);
    treatment.setIsPaid(true);
    treatment.setDetail("detalle");

    Protocol protocol = new Protocol();
    protocol.setIdProtocol(10);
    protocol.setDescription("Protocolo 1");
    protocol.setPrice(BigDecimal.valueOf(200));
    protocol.setIsFinished(false);
    protocol.setTreatments(List.of(treatment));

    when(protocolRepository.findAllByContact_IdContact(contactId)).thenReturn(List.of(protocol));

    // Act
    List<ProtocolResponse> responses = service.getProtocolByContactId(contactId);

    // Assert
    assertEquals(1, responses.size());
    ProtocolResponse response = responses.get(0);
    assertEquals(10, response.getIdProtocol());
    assertEquals("Protocolo 1", response.getDescription());
    assertEquals(BigDecimal.valueOf(200), response.getPrice());
    assertFalse(response.getIsFinished());

    List<ProtocolTreatmentResponse> treatments = response.getTreatments();
    assertEquals(1, treatments.size());

    ProtocolTreatmentResponse t = treatments.get(0);
    assertEquals(100, t.getProductId());
    assertEquals("Tratamiento 1", t.getProductName());
    assertEquals(BigDecimal.TEN, t.getPrice());
    assertTrue(t.getIsPaid());
    assertTrue(t.getIsFinished());
    assertEquals("detalle", t.getDetail());
  }

  @Test
  void shouldThrowWhenNoProtocolsFoundForContact() {
    Integer contactId = 999;
    when(protocolRepository.findAllByContact_IdContact(contactId)).thenReturn(List.of());

    assertThrows(
        ProtocolNotFoundException.class,
        () -> {
          service.getProtocolByContactId(contactId);
        });

    verify(protocolRepository).findAllByContact_IdContact(contactId);
  }
}
