package com.gestion.application.service.performedTreatment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.PerformedTreatmentRequest;
import com.gestion.application.dto.PerformedTreatmentResponse;
import com.gestion.application.model.Contact;
import com.gestion.application.model.PerformedTreatment;
import com.gestion.application.model.Product;
import com.gestion.application.repository.ContactRepository;
import com.gestion.application.repository.PerformedTreatmentRepository;
import com.gestion.application.repository.ProductRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class PerformedTreatmentServiceTest {

  @Mock private PerformedTreatmentRepository performedTreatmentRepository;

  @Mock private ProductRepository productRepository;

  @Mock private ContactRepository contactRepository;

  @InjectMocks private PerformedTreatmentService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldSavePerformedTreatmentSuccessfully() {
    // Given
    PerformedTreatmentRequest request = new PerformedTreatmentRequest();
    request.setContactId(1);
    request.setProductId(2);
    request.setRevisionId(3);
    request.setPerformedDate(LocalDate.of(2025, 6, 14));

    Contact contact = new Contact();
    Product product = new Product();
    product.setName("Product X");

    when(contactRepository.findById(1)).thenReturn(Optional.of(contact));
    when(productRepository.findById(2)).thenReturn(Optional.of(product));

    // When
    service.savePerformedTreatment(request);

    // Then
    verify(performedTreatmentRepository).save(any());
  }

  @Test
  void shouldThrowWhenContactNotFound() {
    // Given
    PerformedTreatmentRequest request = new PerformedTreatmentRequest();
    request.setContactId(99);

    when(contactRepository.findById(99)).thenReturn(Optional.empty());

    // Then
    assertThrows(RuntimeException.class, () -> service.savePerformedTreatment(request));
  }

  @Test
  void shouldThrowWhenProductNotFound() {
    // Given
    PerformedTreatmentRequest request = new PerformedTreatmentRequest();
    request.setContactId(1);
    request.setProductId(999);

    Contact contact = new Contact();
    when(contactRepository.findById(1)).thenReturn(Optional.of(contact));
    when(productRepository.findById(999)).thenReturn(Optional.empty());

    // Then
    assertThrows(RuntimeException.class, () -> service.savePerformedTreatment(request));
  }

  @Test
  void shouldReturnPerformedTreatmentsByContactId() {
    // Given
    Integer contactId = 1;

    PerformedTreatment t1 = new PerformedTreatment();
    t1.setId(10);
    t1.setPerformedDate(LocalDate.of(2025, 6, 14));
    t1.setFinalPrice(100.0);
    t1.setNotes("Nota 1");
    t1.setProductName("Producto 1");
    t1.setProtocolTreatmentId(null);
    t1.setRevisionId(5);

    PerformedTreatment t2 = new PerformedTreatment();
    t2.setId(11);
    t2.setPerformedDate(LocalDate.of(2025, 5, 10));
    t2.setFinalPrice(150.0);
    t2.setNotes("Nota 2");
    t2.setProductName("Producto 2");
    t2.setProtocolTreatmentId(3); // ← from protocol
    t2.setRevisionId(null);

    when(performedTreatmentRepository.findByContactIdContactOrderByPerformedDateDesc(contactId))
        .thenReturn(List.of(t1, t2));

    // When
    List<PerformedTreatmentResponse> responses = service.getTreatmentsByContactId(contactId);

    // Then
    assertEquals(2, responses.size());
    assertEquals("Producto 1", responses.get(0).getProductName());
    assertFalse(responses.get(0).getIsFromProtocol());
    assertTrue(responses.get(1).getIsFromProtocol());
  }
}
