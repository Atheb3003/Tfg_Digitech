package com.gestion.application.service.protocol.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.UpdateProtocolRequest;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.exception.ProtocolNotFoundException;
import com.gestion.application.model.*;
import com.gestion.application.repository.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class UpdateProtocolImplTest {

  @Mock private ProtocolRepository protocolRepository;
  @Mock private ProductRepository productRepository;
  @Mock private ContactRepository contactRepository;
  @Mock private ProtocolTreatmentRepository protocolTreatmentRepository;

  @InjectMocks private UpdateProtocolImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldUpdateProtocolSuccessfully() {
    // Arrange
    Integer protocolId = 1;
    Integer oldContactId = 10;
    Integer newContactId = 20;
    List<Integer> productIds = List.of(100, 200);

    UpdateProtocolRequest request = new UpdateProtocolRequest();
    request.setIdProtocol(protocolId);
    request.setDescription("Updated");
    request.setPrice(BigDecimal.valueOf(123.45));
    request.setContactId(newContactId);
    request.setProductIds(productIds);

    Contact oldContact = new Contact();
    oldContact.setIdContact(oldContactId);

    Contact newContact = new Contact();
    newContact.setIdContact(newContactId);

    Protocol protocol = new Protocol();
    protocol.setIdProtocol(protocolId);
    protocol.setContact(oldContact);
    protocol.setTreatments(List.of(new ProtocolTreatment()));

    Product product1 = new Product();
    Product product2 = new Product();

    when(protocolRepository.findById(protocolId)).thenReturn(Optional.of(protocol));
    when(contactRepository.findById(newContactId)).thenReturn(Optional.of(newContact));
    when(productRepository.findById(100)).thenReturn(Optional.of(product1));
    when(productRepository.findById(200)).thenReturn(Optional.of(product2));
    when(protocolTreatmentRepository.saveAll(anyList()))
        .thenReturn(List.of(new ProtocolTreatment(), new ProtocolTreatment()));
    when(protocolRepository.save(any())).thenReturn(protocol);

    // Act
    Protocol result = service.updateProtocol(request);

    // Assert
    assertNotNull(result);
    assertEquals("Updated", result.getDescription());
    assertEquals(BigDecimal.valueOf(123.45), result.getPrice());
    assertEquals(newContact, result.getContact());
    verify(protocolTreatmentRepository).deleteAll(anyList());
    verify(protocolTreatmentRepository).saveAll(anyList());
    verify(protocolRepository).save(protocol);
  }

  @Test
  void shouldThrowIfProtocolNotFound() {
    UpdateProtocolRequest request = new UpdateProtocolRequest();
    request.setIdProtocol(999);

    when(protocolRepository.findById(999)).thenReturn(Optional.empty());

    assertThrows(ProtocolNotFoundException.class, () -> service.updateProtocol(request));
  }

  @Test
  void shouldThrowIfNewContactNotFound() {
    UpdateProtocolRequest request = new UpdateProtocolRequest();
    request.setIdProtocol(1);
    request.setContactId(2);
    request.setProductIds(List.of());

    Contact existingContact = new Contact();
    existingContact.setIdContact(1);

    Protocol protocol = new Protocol();
    protocol.setContact(existingContact);
    protocol.setTreatments(List.of());

    when(protocolRepository.findById(1)).thenReturn(Optional.of(protocol));
    when(contactRepository.findById(2)).thenReturn(Optional.empty());

    assertThrows(ContactNotFoundException.class, () -> service.updateProtocol(request));
  }

  @Test
  void shouldThrowIfProductNotFound() {
    UpdateProtocolRequest request = new UpdateProtocolRequest();
    request.setIdProtocol(1);
    request.setContactId(1);
    request.setProductIds(List.of(5));

    Contact contact = new Contact();
    contact.setIdContact(1);

    Protocol protocol = new Protocol();
    protocol.setContact(contact);
    protocol.setTreatments(List.of());

    when(protocolRepository.findById(1)).thenReturn(Optional.of(protocol));
    when(contactRepository.findById(1)).thenReturn(Optional.of(contact));
    when(productRepository.findById(5)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> service.updateProtocol(request));
  }
}
