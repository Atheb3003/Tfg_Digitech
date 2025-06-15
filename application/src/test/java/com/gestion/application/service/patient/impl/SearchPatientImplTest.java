package com.gestion.application.service.patient.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ContactResponse;
import com.gestion.application.exception.ProductInvalidDataException;
import com.gestion.application.mapper.ContactMapper;
import com.gestion.application.model.Contact;
import com.gestion.application.repository.ContactRepository;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class SearchPatientImplTest {

  @Mock private ContactRepository repository;

  @Mock private ContactMapper mapper;

  @InjectMocks private SearchPatientImpl searchPatientImpl;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnPageOfContactResponses_whenSearchTermIsValid() {
    // Given
    String term = "juan";
    Pageable pageable = PageRequest.of(0, 10);
    Contact contact = new Contact();
    ContactResponse contactResponse = new ContactResponse();

    Page<Contact> contactPage = new PageImpl<>(Collections.singletonList(contact));
    when(repository.searchPatientsFields("juan", pageable)).thenReturn(contactPage);
    when(mapper.toResponse(contact)).thenReturn(contactResponse);

    // When
    Page<ContactResponse> result = searchPatientImpl.search(term, pageable);

    // Then
    assertNotNull(result);
    assertEquals(1, result.getContent().size());
    verify(repository).searchPatientsFields("juan", pageable);
    verify(mapper).toResponse(contact);
  }

  @Test
  void shouldThrowException_whenSearchTermIsEmpty() {
    // Given
    String term = "  ";
    Pageable pageable = PageRequest.of(0, 10);

    // Then
    assertThrows(ProductInvalidDataException.class, () -> searchPatientImpl.search(term, pageable));
    verifyNoInteractions(repository, mapper);
  }

  @Test
  void shouldThrowException_whenSearchTermIsNull() {
    // Given
    String term = null;
    Pageable pageable = PageRequest.of(0, 10);

    // Then
    assertThrows(ProductInvalidDataException.class, () -> searchPatientImpl.search(term, pageable));
    verifyNoInteractions(repository, mapper);
  }
}
