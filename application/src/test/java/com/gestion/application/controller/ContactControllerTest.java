package com.gestion.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.mapper.ContactMapper;
import com.gestion.application.model.Contact;
import com.gestion.application.service.contact.ContactService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class ContactControllerTest {

  @Mock private ContactService contactService;

  @Mock private ContactMapper contactMapper;

  @InjectMocks private ContactController controller;

  private Contact contactEntity;
  private ContactRequest request;
  private ContactResponse response;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);

    request = new ContactRequest();
    contactEntity = new Contact(); // Suponiendo que tienes una entidad Contact real
    response = new ContactResponse();
    response.setIdContact(1);
  }

  @Test
  void testCreateContact() {
    when(contactMapper.toEntity(request)).thenReturn(contactEntity);
    when(contactService.crearContacto(contactEntity)).thenReturn(contactEntity);
    when(contactMapper.toResponse(contactEntity)).thenReturn(response);

    var result = controller.createContact(request);

    assertEquals(201, result.getStatusCodeValue());
    assertEquals("created", result.getBody().getStatus());
    assertEquals(response, result.getBody().getData());
  }

  @Test
  void testEditContact() {
    when(contactService.editarContacto(1, request)).thenReturn(contactEntity);
    when(contactMapper.toResponse(contactEntity)).thenReturn(response);

    var result = controller.editContact(1, request);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("updated", result.getBody().getStatus());
    assertEquals(response, result.getBody().getData());
  }

  @Test
  void testDeleteContact() {
    doNothing().when(contactService).deleteContact(1);

    var result = controller.deleteContact(1);

    assertEquals(200, result.getStatusCodeValue());
    assertTrue(result.getBody().getMessage().contains("eliminado correctamente"));
  }

  @Test
  void testGetContactDetails() {
    when(contactService.getContactAllDetails(1)).thenReturn(response);

    var result = controller.getContactDetails(1);

    assertEquals(response, result);
  }

  @Test
  void testGetNonPatients() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<ContactResponse> page = new PageImpl<>(List.of(response));

    when(contactService.getContactsWithoutPatient(pageable)).thenReturn(page);

    var result = controller.getNonPatients(pageable);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("success", result.getBody().getStatus());
    assertEquals(1, result.getBody().getData().getContent().size());
  }

  @Test
  void testSearchContacts() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<ContactResponse> page = new PageImpl<>(List.of(response));

    when(contactService.searchContacts("ana", pageable)).thenReturn(page);

    var result = controller.searchContacts("ana", pageable);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("success", result.getBody().getStatus());
    assertEquals(1, result.getBody().getData().getContent().size());
  }
}
