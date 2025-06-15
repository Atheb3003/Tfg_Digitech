package com.gestion.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.service.contact.ContactService;
import com.gestion.application.service.patient.PatientService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class PatientControllerTest {

  @Mock private ContactService contactService;

  @Mock private PatientService patientService;

  @InjectMocks private PatientController controller;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testTransformContactToPatient() {
    ContactToPatientResponse response = new ContactToPatientResponse("created", "Paciente creado");
    when(contactService.transformarEnPaciente(1)).thenReturn(response);

    var result = controller.transformContactToPatient(1);

    assertEquals(201, result.getStatusCodeValue());
    assertEquals("created", result.getBody().getStatus());
  }

  @Test
  void testGetPatients() {
    ContactResponse contact = new ContactResponse();
    Page<ContactResponse> page = new PageImpl<>(List.of(contact));
    Pageable pageable = PageRequest.of(0, 10);

    when(contactService.getContactsWithPatient(pageable)).thenReturn(page);

    var result = controller.getPatients(pageable);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("success", result.getBody().getStatus());
    assertEquals(1, result.getBody().getData().getContent().size());
  }

  @Test
  void testGetPatientById() {
    PatientResponse patient = new PatientResponse();
    patient.setIdPatient(99);
    patient.setIdContact(1);
    patient.setIsVisible(true);
    patient.setDischargeDate(LocalDate.now());

    when(patientService.getPatient(99)).thenReturn(patient);

    var result = controller.getPatientById(99);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("success", result.getBody().getStatus());
    assertEquals(99, result.getBody().getData().getIdPatient());
  }

  @Test
  void testSearchContacts() {
    ContactResponse contact = new ContactResponse();
    Page<ContactResponse> page = new PageImpl<>(List.of(contact));
    Pageable pageable = PageRequest.of(0, 10);

    when(patientService.searchContacts("ana", pageable)).thenReturn(page);

    var result = controller.searchContacts("ana", pageable);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("success", result.getBody().getStatus());
    assertEquals(1, result.getBody().getData().getContent().size());
  }

  @Test
  void testGetPatientIdByContact_found() {
    when(patientService.getPatientIdByContact(1)).thenReturn(Optional.of(42));

    var result = controller.getPatientIdByContact(1);

    assertEquals(200, result.getStatusCodeValue());
    ApiResponse<?> response = (ApiResponse<?>) result.getBody();
    assertEquals("found", response.getStatus());
    assertEquals(42, response.getData());
  }

  @Test
  void testGetPatientIdByContact_notFound() {
    when(patientService.getPatientIdByContact(1)).thenReturn(Optional.empty());

    var result = controller.getPatientIdByContact(1);

    assertEquals(204, result.getStatusCodeValue());
    assertNull(result.getBody());
  }
}
