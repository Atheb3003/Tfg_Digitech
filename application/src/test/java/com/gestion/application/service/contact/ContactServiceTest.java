package com.gestion.application.service.contact;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.mapper.ContactMapper;
import com.gestion.application.model.Contact;
import com.gestion.application.service.contact.impl.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class ContactServiceTest {

  @Mock private CreateContactImpl createContact;
  @Mock private EditContactImpl editContact;
  @Mock private DeleteContactImpl deleteContact;
  @Mock private GetContactAllDetailsImpl getContactAllDetails;
  @Mock private TransformContactToPatientImpl transformContactToPatient;
  @Mock private GetContactsWithPatientImpl getContactsWithPatient;
  @Mock private GetContactsWithoutPatientImpl getContactsWithoutPatient;
  @Mock private ContactMapper contactMapper;
  @Mock private SearchContactsImpl searchImpl;

  @InjectMocks private ContactService contactService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldCreateContact() {
    Contact contact = new Contact();
    when(createContact.crearContacto(contact)).thenReturn(contact);

    Contact result = contactService.crearContacto(contact);

    assertEquals(contact, result);
    verify(createContact).crearContacto(contact);
  }

  @Test
  void shouldEditContact() {
    ContactRequest request = new ContactRequest();
    Contact updated = new Contact();
    when(editContact.editContact(1, request)).thenReturn(updated);

    Contact result = contactService.editarContacto(1, request);

    assertEquals(updated, result);
    verify(editContact).editContact(1, request);
  }

  @Test
  void shouldDeleteContact() {
    contactService.deleteContact(1);
    verify(deleteContact).deleteContact(1);
  }

  @Test
  void shouldGetContactAllDetails() {
    ContactResponse response = new ContactResponse();
    when(getContactAllDetails.getContactById(1)).thenReturn(response);

    ContactResponse result = contactService.getContactAllDetails(1);

    assertEquals(response, result);
    verify(getContactAllDetails).getContactById(1);
  }

  @Test
  void shouldTransformToPatient() {
    ContactToPatientResponse resp = new ContactToPatientResponse("created", "msg");
    when(transformContactToPatient.transformContactToPatient(1)).thenReturn(resp);

    ContactToPatientResponse result = contactService.transformarEnPaciente(1);

    assertEquals(resp, result);
    verify(transformContactToPatient).transformContactToPatient(1);
  }

  @Test
  void shouldGetContactsWithPatient() {
    Pageable pageable = PageRequest.of(0, 5);
    Page<ContactResponse> page = new PageImpl<>(List.of(new ContactResponse()));
    when(getContactsWithPatient.getContactsWithPatient(pageable)).thenReturn(page);

    Page<ContactResponse> result = contactService.getContactsWithPatient(pageable);

    assertEquals(page, result);
    verify(getContactsWithPatient).getContactsWithPatient(pageable);
  }

  @Test
  void shouldGetContactsWithoutPatient() {
    Pageable pageable = PageRequest.of(0, 5);
    Page<ContactResponse> page = new PageImpl<>(List.of(new ContactResponse()));
    when(getContactsWithoutPatient.getContactsWithoutPatient(pageable)).thenReturn(page);

    Page<ContactResponse> result = contactService.getContactsWithoutPatient(pageable);

    assertEquals(page, result);
    verify(getContactsWithoutPatient).getContactsWithoutPatient(pageable);
  }

  @Test
  void shouldSearchContacts() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<ContactResponse> expected = new PageImpl<>(List.of(new ContactResponse()));
    when(searchImpl.search("john", pageable)).thenReturn(expected);

    Page<ContactResponse> result = contactService.searchContacts("john", pageable);

    assertEquals(expected, result);
    verify(searchImpl).search("john", pageable);
  }
}
