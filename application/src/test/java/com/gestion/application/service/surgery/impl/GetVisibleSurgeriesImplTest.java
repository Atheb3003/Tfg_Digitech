package com.gestion.application.service.surgery.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.FullDetailSurgeryResponse;
import com.gestion.application.model.Contact;
import com.gestion.application.model.Patient;
import com.gestion.application.model.Surgery;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.SurgeryRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class GetVisibleSurgeriesImplTest {

  @Mock private SurgeryRepository surgeryRepository;

  @InjectMocks private GetVisibleSurgeriesImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getVisibleSurgeries_shouldReturnPagedFullDetailResponses() {
    Pageable pageable = PageRequest.of(0, 10);

    Contact contact = new Contact();
    contact.setIdContact(500);
    contact.setIdContactString("CT500");
    contact.setName("Charlie");
    contact.setSurname("Brown");

    Patient patient = new Patient();
    patient.setIdPatient(50);
    patient.setContact(contact);

    SurgeryReservation reservation = new SurgeryReservation();
    reservation.setIdSurgeryReservation(5000);
    reservation.setFollicularUnits(200);
    reservation.setSurgicalTechnique("Technique Q");
    reservation.setNational(true);
    reservation.setDeposit(BigDecimal.valueOf(700));
    reservation.setSurgeryPrice(BigDecimal.valueOf(1500));
    reservation.setRemainingMoney(BigDecimal.valueOf(800));
    reservation.setPatient(patient);

    Surgery surgery = new Surgery();
    surgery.setIdSurgery(1000);
    surgery.setDate(LocalDate.of(2025, 6, 14));
    surgery.setObservations("Observation example");
    surgery.setIsVisible(true);
    surgery.setSurgeryReservation(reservation);

    Page<Surgery> page = new PageImpl<>(List.of(surgery), pageable, 1);

    when(surgeryRepository.findAllByIsVisibleTrue(pageable)).thenReturn(page);

    Page<FullDetailSurgeryResponse> result = service.getVisibleSurgeries(pageable);

    assertNotNull(result);
    assertEquals(1, result.getTotalElements());

    FullDetailSurgeryResponse dto = result.getContent().get(0);

    assertEquals(surgery.getIdSurgery(), dto.getIdSurgery());
    assertEquals(surgery.getDate(), dto.getDate());
    assertEquals(surgery.getObservations(), dto.getObservations());
    assertTrue(dto.getIsVisible());

    assertEquals(reservation.getIdSurgeryReservation(), dto.getIdReservation());
    assertEquals(reservation.getFollicularUnits(), dto.getFollicularUnits());
    assertEquals(reservation.getSurgicalTechnique(), dto.getSurgicalTechnique());
    assertEquals(reservation.getNational(), dto.getNational());
    assertEquals(reservation.getDeposit(), dto.getDeposit());
    assertEquals(reservation.getSurgeryPrice(), dto.getSurgeryPrice());
    assertEquals(reservation.getRemainingMoney(), dto.getRemainingMoney());

    assertEquals(patient.getIdPatient(), dto.getIdPatient());
    assertEquals(contact.getIdContact(), dto.getIdContact());
    assertEquals(contact.getIdContactString(), dto.getIdContactString());
    assertEquals("Charlie Brown", dto.getContactFullName());

    verify(surgeryRepository).findAllByIsVisibleTrue(pageable);
  }

  @Test
  void getVisibleSurgeries_shouldHandleNullNestedFieldsGracefully() {
    Pageable pageable = PageRequest.of(0, 10);

    Surgery surgery = new Surgery();
    surgery.setIdSurgery(2000);
    surgery.setDate(LocalDate.of(2025, 1, 1));
    surgery.setObservations("No reservation");
    surgery.setIsVisible(true);
    surgery.setSurgeryReservation(null); // no reservation linked

    Page<Surgery> page = new PageImpl<>(List.of(surgery), pageable, 1);

    when(surgeryRepository.findAllByIsVisibleTrue(pageable)).thenReturn(page);

    Page<FullDetailSurgeryResponse> result = service.getVisibleSurgeries(pageable);

    assertNotNull(result);
    assertEquals(1, result.getTotalElements());

    FullDetailSurgeryResponse dto = result.getContent().get(0);

    assertEquals(surgery.getIdSurgery(), dto.getIdSurgery());
    assertEquals(surgery.getDate(), dto.getDate());
    assertEquals(surgery.getObservations(), dto.getObservations());
    assertTrue(dto.getIsVisible());

    // Nested fields should be null or defaults since no reservation linked
    assertNull(dto.getIdReservation());
    assertNull(dto.getFollicularUnits());
    assertNull(dto.getSurgicalTechnique());
    assertNull(dto.getNational());
    assertNull(dto.getDeposit());
    assertNull(dto.getSurgeryPrice());
    assertNull(dto.getRemainingMoney());
    assertNull(dto.getIdPatient());
    assertNull(dto.getIdContact());
    assertNull(dto.getIdContactString());
    assertNull(dto.getContactFullName());

    verify(surgeryRepository).findAllByIsVisibleTrue(pageable);
  }
}
