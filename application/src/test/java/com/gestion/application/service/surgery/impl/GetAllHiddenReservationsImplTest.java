package com.gestion.application.service.surgery.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.SurgeryReservationResponse;
import com.gestion.application.model.Contact;
import com.gestion.application.model.Patient;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.SurgeryReservationRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class GetAllHiddenReservationsImplTest {

  @Mock private SurgeryReservationRepository reservationRepository;

  @InjectMocks private GetAllHiddenReservationsImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getAllHidden_shouldReturnPagedResponses() {
    Pageable pageable = PageRequest.of(0, 10);

    Contact contact = new Contact();
    contact.setIdContact(100);
    contact.setName("John");
    contact.setSurname("Doe");

    Patient patient = new Patient();
    patient.setIdPatient(1);
    patient.setContact(contact);

    SurgeryReservation reservation = new SurgeryReservation();
    reservation.setIdSurgeryReservation(10);
    reservation.setPatient(patient);
    reservation.setDescription("Desc");
    reservation.setFollicularUnits(50);
    reservation.setSurgicalTechnique("Technique");
    reservation.setEstimatedDate(LocalDate.now().plusDays(5));
    reservation.setNational(true);
    reservation.setDeposit(BigDecimal.valueOf(200));
    reservation.setSurgeryPrice(BigDecimal.valueOf(500));
    reservation.setIsVisible(false);
    reservation.setConfirmed(true);
    reservation.setRemainingMoney(BigDecimal.valueOf(300));
    reservation.setIsPaid(false);

    Page<SurgeryReservation> page = new PageImpl<>(List.of(reservation), pageable, 1);

    when(reservationRepository.findAllByIsVisibleFalse(pageable)).thenReturn(page);

    Page<SurgeryReservationResponse> result = service.getAllHidden(pageable);

    assertNotNull(result);
    assertEquals(1, result.getTotalElements());
    SurgeryReservationResponse dto = result.getContent().get(0);

    assertEquals(reservation.getIdSurgeryReservation(), dto.getIdSurgeryReservation());
    assertEquals(patient.getIdPatient(), dto.getIdPatient());
    assertEquals(contact.getIdContact(), dto.getIdContact());
    assertEquals("John Doe", dto.getContactFullName());
    assertEquals(reservation.getDescription(), dto.getDescription());
    assertEquals(reservation.getFollicularUnits(), dto.getFollicularUnits());
    assertEquals(reservation.getSurgicalTechnique(), dto.getSurgicalTechnique());
    assertEquals(reservation.getEstimatedDate(), dto.getEstimatedDate());
    assertEquals(reservation.getNational(), dto.getNational());
    assertEquals(reservation.getDeposit(), dto.getDeposit());
    assertEquals(reservation.getSurgeryPrice(), dto.getSurgeryPrice());
    assertEquals(reservation.getIsVisible(), dto.getIsVisible());
    assertEquals(reservation.getConfirmed(), dto.getConfirmed());
    assertEquals(reservation.getRemainingMoney(), dto.getRemainingMoney());
    assertEquals(reservation.getIsPaid(), dto.getIsPaid());

    verify(reservationRepository).findAllByIsVisibleFalse(pageable);
  }
}
