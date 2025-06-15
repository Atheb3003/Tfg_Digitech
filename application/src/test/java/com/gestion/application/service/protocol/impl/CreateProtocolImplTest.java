package com.gestion.application.service.protocol.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.CreateProtocolRequest;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.model.*;
import com.gestion.application.repository.*;
import java.math.BigDecimal;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class CreateProtocolImplTest {

  @Mock private ProtocolRepository protocolRepository;
  @Mock private ProductRepository productRepository;
  @Mock private ContactRepository contactRepository;
  @Mock private ProtocolTreatmentRepository protocolTreatmentRepository;

  @InjectMocks private CreateProtocolImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldCreateProtocolWithTreatments() {
    // Arrange
    Integer contactId = 1;
    List<Integer> productIds = List.of(10, 20);

    CreateProtocolRequest request = new CreateProtocolRequest();
    request.setContactId(contactId);
    request.setDescription("Test Protocol");
    request.setPrice(BigDecimal.valueOf(100));
    request.setProductIds(productIds);

    Contact contact = new Contact();
    when(contactRepository.findById(contactId)).thenReturn(Optional.of(contact));

    Protocol savedProtocol = new Protocol();
    savedProtocol.setIdProtocol(999);
    when(protocolRepository.save(any(Protocol.class))).thenReturn(savedProtocol);

    Product product1 = new Product();
    Product product2 = new Product();
    when(productRepository.findById(10)).thenReturn(Optional.of(product1));
    when(productRepository.findById(20)).thenReturn(Optional.of(product2));

    // Act
    Protocol result = service.createProtocol(request);

    // Assert
    assertNotNull(result);
    assertEquals(999, result.getIdProtocol());
    verify(protocolRepository).save(any());
    verify(protocolTreatmentRepository).saveAll(anyList());
  }

  @Test
  void shouldCreateProtocolWithoutProducts() {
    CreateProtocolRequest request = new CreateProtocolRequest();
    request.setContactId(1);
    request.setDescription("Protocol sin productos");
    request.setPrice(BigDecimal.TEN);
    request.setProductIds(Collections.emptyList());

    Contact contact = new Contact();
    when(contactRepository.findById(1)).thenReturn(Optional.of(contact));

    Protocol savedProtocol = new Protocol();
    when(protocolRepository.save(any())).thenReturn(savedProtocol);

    Protocol result = service.createProtocol(request);

    assertNotNull(result);
    verify(protocolRepository).save(any());
    verify(protocolTreatmentRepository, never()).saveAll(any());
  }

  @Test
  void shouldThrowWhenContactNotFound() {
    CreateProtocolRequest request = new CreateProtocolRequest();
    request.setContactId(999);

    when(contactRepository.findById(999)).thenReturn(Optional.empty());

    assertThrows(ContactNotFoundException.class, () -> service.createProtocol(request));
    verify(protocolRepository, never()).save(any());
  }

  @Test
  void shouldThrowWhenProductNotFound() {
    CreateProtocolRequest request = new CreateProtocolRequest();
    request.setContactId(1);
    request.setDescription("Test");
    request.setPrice(BigDecimal.ONE);
    request.setProductIds(List.of(1));

    Contact contact = new Contact();
    when(contactRepository.findById(1)).thenReturn(Optional.of(contact));
    when(protocolRepository.save(any())).thenReturn(new Protocol());
    when(productRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> service.createProtocol(request));
    verify(protocolTreatmentRepository, never()).saveAll(any());
  }
}
