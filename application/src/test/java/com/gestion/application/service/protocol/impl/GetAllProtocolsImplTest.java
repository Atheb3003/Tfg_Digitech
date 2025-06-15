package com.gestion.application.service.protocol.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ProtocolListResponse;
import com.gestion.application.model.Contact;
import com.gestion.application.model.Protocol;
import com.gestion.application.repository.ProtocolRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class GetAllProtocolsImplTest {

  @Mock private ProtocolRepository protocolRepository;

  @InjectMocks private GetAllProtocolsImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnMappedProtocolListResponse() {
    // Arrange
    Contact contact = new Contact();
    contact.setName("Juan");
    contact.setSurname("Pérez");
    contact.setTelephoneNumber1("123456789");
    contact.setTelephoneNumber2("987654321");
    contact.setNif("12345678A");

    Protocol protocol = new Protocol();
    protocol.setIdProtocol(1);
    protocol.setDescription("Test Protocol");
    protocol.setPrice(BigDecimal.TEN);
    protocol.setIsFinished(false);
    protocol.setContact(contact);

    when(protocolRepository.findAll()).thenReturn(List.of(protocol));

    // Act
    List<ProtocolListResponse> result = service.getAllProtocols();

    // Assert
    assertEquals(1, result.size());
    ProtocolListResponse response = result.get(0);

    assertEquals(1, response.getIdProtocol());
    assertEquals("Test Protocol", response.getDescription());
    assertEquals(BigDecimal.TEN, response.getPrice());
    assertEquals(false, response.getIsFinished());
    assertEquals("Juan Pérez", response.getContactName());
    assertEquals("123456789", response.getContactTelephone());
    assertEquals("12345678A", response.getContactNif());
  }

  @Test
  void shouldReturnEmptyListWhenNoProtocolsExist() {
    when(protocolRepository.findAll()).thenReturn(List.of());

    List<ProtocolListResponse> result = service.getAllProtocols();

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }
}
