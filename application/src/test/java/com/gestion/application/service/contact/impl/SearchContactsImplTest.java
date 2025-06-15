package com.gestion.application.service.contact.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ContactResponse;
import com.gestion.application.exception.ProductInvalidDataException;
import com.gestion.application.mapper.ContactMapper;
import com.gestion.application.model.Contact;
import com.gestion.application.repository.ContactRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class SearchContactsImplTest {

  @Mock private ContactRepository repository;

  @Mock private ContactMapper mapper;

  @InjectMocks private SearchContactsImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnContactPageWhenSearchTermIsValid() {
    // Given
    String term = "juan";
    Pageable pageable = PageRequest.of(0, 10);
    Contact contact = new Contact();
    contact.setIdContact(1);
    ContactResponse response = new ContactResponse();
    response.setIdContact(1);

    Page<Contact> contactPage = new PageImpl<>(List.of(contact));
    when(repository.searchAllFields("juan", pageable)).thenReturn(contactPage);
    when(mapper.toResponse(contact)).thenReturn(response);

    // When
    Page<ContactResponse> result = service.search(term, pageable);

    // Then
    assertEquals(1, result.getTotalElements());
    assertEquals(1, result.getContent().get(0).getIdContact());
    verify(repository).searchAllFields("juan", pageable);
    verify(mapper).toResponse(contact);
  }

  @Test
  void shouldThrowWhenSearchTermIsNull() {
    assertThrows(ProductInvalidDataException.class, () -> service.search(null, Pageable.unpaged()));
  }

  @Test
  void shouldThrowWhenSearchTermIsEmpty() {
    assertThrows(
        ProductInvalidDataException.class, () -> service.search("   ", Pageable.unpaged()));
  }
}
