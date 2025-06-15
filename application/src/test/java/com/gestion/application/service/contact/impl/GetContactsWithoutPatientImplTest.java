package com.gestion.application.service.contact.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ContactResponse;
import com.gestion.application.mapper.ContactMapper;
import com.gestion.application.model.Contact;
import com.gestion.application.repository.ContactRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class GetContactsWithoutPatientImplTest {

  @Mock private ContactRepository contactRepository;

  @Mock private ContactMapper contactMapper;

  @InjectMocks private GetContactsWithoutPatientImpl getContactsWithoutPatient;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnPageOfContactResponses() {
    // Given
    Pageable pageable = PageRequest.of(0, 2, Sort.by("idContact").descending());

    Contact contact1 = new Contact();
    contact1.setIdContact(1);
    Contact contact2 = new Contact();
    contact2.setIdContact(2);

    List<Contact> contactList = List.of(contact1, contact2);
    Page<Contact> contactPage = new PageImpl<>(contactList, pageable, contactList.size());

    ContactResponse response1 = new ContactResponse();
    response1.setIdContact(1);
    ContactResponse response2 = new ContactResponse();
    response2.setIdContact(2);

    when(contactRepository.findAllWithoutPatientOrderByIdDesc(pageable)).thenReturn(contactPage);
    when(contactMapper.toResponse(contact1)).thenReturn(response1);
    when(contactMapper.toResponse(contact2)).thenReturn(response2);

    // When
    Page<ContactResponse> result = getContactsWithoutPatient.getContactsWithoutPatient(pageable);

    // Then
    assertEquals(2, result.getContent().size());
    assertEquals(1, result.getContent().get(0).getIdContact());
    assertEquals(2, result.getContent().get(1).getIdContact());

    verify(contactRepository).findAllWithoutPatientOrderByIdDesc(pageable);
    verify(contactMapper, times(2)).toResponse(any(Contact.class));
  }
}
