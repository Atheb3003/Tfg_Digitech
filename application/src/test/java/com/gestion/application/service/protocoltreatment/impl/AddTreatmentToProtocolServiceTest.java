package com.gestion.application.service.protocoltreatment.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.AddTreatmentToProtocolRequest;
import com.gestion.application.model.Product;
import com.gestion.application.model.Protocol;
import com.gestion.application.model.ProtocolTreatment;
import com.gestion.application.repository.ProductRepository;
import com.gestion.application.repository.ProtocolRepository;
import com.gestion.application.repository.ProtocolTreatmentRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class AddTreatmentToProtocolServiceTest {

  @Mock private ProtocolRepository protocolRepository;
  @Mock private ProductRepository productRepository;
  @Mock private ProtocolTreatmentRepository treatmentRepository;

  @InjectMocks private AddTreatmentToProtocolService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldAddTreatmentAndUpdateProtocolPrice() {
    // Arrange
    AddTreatmentToProtocolRequest request = new AddTreatmentToProtocolRequest();
    request.setProtocolId(1);
    request.setProductId(2);
    request.setDetail("Detail");
    request.setPrice(BigDecimal.valueOf(50));

    Protocol protocol = new Protocol();
    protocol.setPrice(BigDecimal.valueOf(100));

    Product product = new Product();

    when(protocolRepository.findById(1)).thenReturn(Optional.of(protocol));
    when(productRepository.findById(2)).thenReturn(Optional.of(product));
    when(treatmentRepository.save(any(ProtocolTreatment.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));
    when(protocolRepository.save(protocol)).thenReturn(protocol);

    // Act
    service.addTreatment(request);

    // Assert
    verify(treatmentRepository).save(any(ProtocolTreatment.class));
    verify(protocolRepository).save(protocol);

    assertEquals(BigDecimal.valueOf(150), protocol.getPrice());
  }

  @Test
  void shouldThrowWhenProtocolNotFound() {
    AddTreatmentToProtocolRequest request = new AddTreatmentToProtocolRequest();
    request.setProtocolId(1);

    when(protocolRepository.findById(1)).thenReturn(Optional.empty());

    RuntimeException ex =
        assertThrows(
            RuntimeException.class,
            () -> {
              service.addTreatment(request);
            });

    assertEquals("Protocol not found", ex.getMessage());
  }

  @Test
  void shouldThrowWhenProductNotFound() {
    AddTreatmentToProtocolRequest request = new AddTreatmentToProtocolRequest();
    request.setProtocolId(1);
    request.setProductId(2);

    Protocol protocol = new Protocol();

    when(protocolRepository.findById(1)).thenReturn(Optional.of(protocol));
    when(productRepository.findById(2)).thenReturn(Optional.empty());

    RuntimeException ex =
        assertThrows(
            RuntimeException.class,
            () -> {
              service.addTreatment(request);
            });

    assertEquals("Product not found", ex.getMessage());
  }
}
