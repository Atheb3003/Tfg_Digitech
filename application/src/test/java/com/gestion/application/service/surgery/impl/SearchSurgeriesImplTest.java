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

class SearchSurgeriesImplTest {

  @Mock private SurgeryRepository surgeryRepository;

  @InjectMocks private SearchSurgeriesImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void searchVisibleSurgeries_shouldReturnPagedFullDetailResponses() {
    String searchTerm = "test search";
    Pageable pageable = PageRequest.of(0, 10);

    Contact contact = new Contact();
    contact.setIdContact(700);
    contact.setIdContactString("CT700");
    contact.setName("Eve");
    contact.setSurname("Adams");

    Patient patient = new Patient();
    patient.setIdPatient(70);
    patient.setContact(contact);

    SurgeryReservation reservation = new SurgeryReservation();
    reservation.setIdSurgeryReservation(7000);
    reservation.setFollicularUnits(300);
    reservation.setSurgicalTechnique("Technique S");
    reservation.setNational(false);
    reservation.setDeposit(BigDecimal.valueOf(1000));
    reservation.setSurgeryPrice(BigDecimal.valueOf(2000));
    reservation.setRemainingMoney(BigDecimal.valueOf(1000));
    reservation.setPatient(patient);

    Surgery surgery = new Surgery();
    surgery.setIdSurgery(1500);
    surgery.setDate(LocalDate.of(2025, 12, 1));
    surgery.setObservations("Surgery observation");
    surgery.setIsVisible(true);
    surgery.setSurgeryReservation(reservation);

    Page<Surgery> page = new PageImpl<>(List.of(surgery), pageable, 1);

    when(surgeryRepository.searchVisibleSurgeries(searchTerm, pageable)).thenReturn(page);

    Page<FullDetailSurgeryResponse> result = service.searchVisibleSurgeries(searchTerm, pageable);

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
    assertEquals("Eve Adams", dto.getContactFullName());

    verify(surgeryRepository).searchVisibleSurgeries(searchTerm, pageable);
  }

  @Test
  void searchVisibleSurgeries_shouldHandleNullNestedFields() {
    String searchTerm = "empty";
    Pageable pageable = PageRequest.of(0, 10);

    Surgery surgery = new Surgery();
    surgery.setIdSurgery(2500);
    surgery.setDate(LocalDate.of(2026, 1, 1));
    surgery.setObservations("No reservation");
    surgery.setIsVisible(true);
    surgery.setSurgeryReservation(null);

    Page<Surgery> page = new PageImpl<>(List.of(surgery), pageable, 1);

    when(surgeryRepository.searchVisibleSurgeries(searchTerm, pageable)).thenReturn(page);

    Page<FullDetailSurgeryResponse> result = service.searchVisibleSurgeries(searchTerm, pageable);

    assertNotNull(result);
    assertEquals(1, result.getTotalElements());

    FullDetailSurgeryResponse dto = result.getContent().get(0);

    assertEquals(surgery.getIdSurgery(), dto.getIdSurgery());
    assertEquals(surgery.getDate(), dto.getDate());
    assertEquals(surgery.getObservations(), dto.getObservations());
    assertTrue(dto.getIsVisible());

    // Nested fields null since no reservation or patient/contact
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

    verify(surgeryRepository).searchVisibleSurgeries(searchTerm, pageable);
  }
}
