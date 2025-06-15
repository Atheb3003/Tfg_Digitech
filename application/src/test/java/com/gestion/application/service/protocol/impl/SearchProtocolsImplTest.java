package com.gestion.application.service.protocol.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ProtocolSearchResponseDto;
import com.gestion.application.dto.ProtocolSearchTreatmentDto;
import com.gestion.application.model.*;
import com.gestion.application.repository.ProtocolRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

public class SearchProtocolsImplTest {

  @Mock private ProtocolRepository protocolRepository;

  @InjectMocks private SearchProtocolsImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnMappedSearchResults() {
    // Arrange
    Product product = new Product();
    product.setName("Producto 1");

    ProtocolTreatment treatment = new ProtocolTreatment();
    treatment.setId(1);
    treatment.setPrice(BigDecimal.TEN);
    treatment.setIsPaid(true);
    treatment.setIsFinished(true);
    treatment.setProduct(product);

    Contact contact = new Contact();
    contact.setIdContactString("C-123");

    Protocol protocol = new Protocol();
    protocol.setIdProtocol(100);
    protocol.setDescription("Protocolo A");
    protocol.setIsFinished(false);
    protocol.setContact(contact);
    protocol.setTreatments(List.of(treatment));

    Page<Protocol> page = new PageImpl<>(List.of(protocol));
    when(protocolRepository.searchProtocols(eq("A"), any())).thenReturn(page);

    Pageable pageable = PageRequest.of(0, 10);

    // Act
    Page<ProtocolSearchResponseDto> result = service.searchProtocols("A", pageable);

    // Assert
    assertEquals(1, result.getContent().size());
    ProtocolSearchResponseDto dto = result.getContent().get(0);
    assertEquals(100, dto.getProtocolId());
    assertEquals("Protocolo A", dto.getProtocolDescription());
    assertEquals("C-123", dto.getContactIdString());
    assertEquals(BigDecimal.TEN, dto.getProtocolTotalPrice());

    List<ProtocolSearchTreatmentDto> treatments = dto.getTreatments();
    assertEquals(1, treatments.size());
    ProtocolSearchTreatmentDto t = treatments.get(0);
    assertEquals(1, t.getTreatmentId());
    assertEquals("Producto 1", t.getProductName());
    assertTrue(t.getPaid());
    assertTrue(t.getComplete());
  }

  @Test
  void shouldHandleNullContactAndTreatmentsGracefully() {
    Protocol protocol = new Protocol();
    protocol.setIdProtocol(200);
    protocol.setDescription("Protocolo B");
    protocol.setIsFinished(true);
    protocol.setContact(null);
    protocol.setTreatments(null); // deliberately null

    Page<Protocol> page = new PageImpl<>(List.of(protocol));
    when(protocolRepository.searchProtocols(eq("B"), any())).thenReturn(page);

    Pageable pageable = PageRequest.of(0, 10);

    Page<ProtocolSearchResponseDto> result = service.searchProtocols("B", pageable);

    assertEquals(1, result.getContent().size());
    ProtocolSearchResponseDto dto = result.getContent().get(0);

    assertEquals(200, dto.getProtocolId());
    assertNull(dto.getContactIdString());
    assertEquals(BigDecimal.ZERO, dto.getProtocolTotalPrice());
    assertTrue(dto.getTreatments().isEmpty());
  }
}
