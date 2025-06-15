package com.gestion.application.service.performedTreatment;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.PerformedTreatmentFromProtocolRequest;
import com.gestion.application.model.Contact;
import com.gestion.application.model.PerformedTreatment;
import com.gestion.application.model.Product;
import com.gestion.application.model.ProtocolTreatment;
import com.gestion.application.repository.ContactRepository;
import com.gestion.application.repository.PerformedTreatmentRepository;
import com.gestion.application.repository.ProtocolTreatmentRepository;
import com.gestion.application.repository.RevisionRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class PerformedTreatmentFromProtocolServiceTest {

  @Mock private PerformedTreatmentRepository performedTreatmentRepository;

  @Mock private ContactRepository contactRepository;

  @Mock private ProtocolTreatmentRepository protocolTreatmentRepository;

  @Mock private RevisionRepository revisionRepository;

  @InjectMocks private PerformedTreatmentFromProtocolService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldCreatePerformedTreatmentFromProtocolSuccessfully() {
    // Given
    Contact contact = new Contact();
    Product product = new Product();
    product.setName("Tratamiento A");

    ProtocolTreatment protocol = new ProtocolTreatment();
    protocol.setProduct(product);

    PerformedTreatmentFromProtocolRequest request = new PerformedTreatmentFromProtocolRequest();
    request.setContactId(1);
    request.setProtocolTreatmentId(2);
    request.setPerformedDate(LocalDate.of(2025, 6, 14));
    request.setFinalPrice(100.0);
    request.setNotes("Observaciones");
    request.setRevisionId(5);

    when(contactRepository.findById(1)).thenReturn(Optional.of(contact));
    when(protocolTreatmentRepository.findById(2)).thenReturn(Optional.of(protocol));

    // When
    service.createFromProtocol(request);

    // Then
    verify(performedTreatmentRepository).save(any(PerformedTreatment.class));
  }

  @Test
  void shouldThrowWhenContactNotFound() {
    // Given
    PerformedTreatmentFromProtocolRequest request = new PerformedTreatmentFromProtocolRequest();
    request.setContactId(99);
    request.setProtocolTreatmentId(2);

    when(contactRepository.findById(99)).thenReturn(Optional.empty());

    // Then
    assertThrows(RuntimeException.class, () -> service.createFromProtocol(request));
  }

  @Test
  void shouldThrowWhenProtocolNotFound() {
    // Given
    Contact contact = new Contact();
    PerformedTreatmentFromProtocolRequest request = new PerformedTreatmentFromProtocolRequest();
    request.setContactId(1);
    request.setProtocolTreatmentId(99);

    when(contactRepository.findById(1)).thenReturn(Optional.of(contact));
    when(protocolTreatmentRepository.findById(99)).thenReturn(Optional.empty());

    // Then
    assertThrows(RuntimeException.class, () -> service.createFromProtocol(request));
  }
}
