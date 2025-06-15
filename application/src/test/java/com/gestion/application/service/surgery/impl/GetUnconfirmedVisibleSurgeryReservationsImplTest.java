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

class GetUnconfirmedVisibleSurgeryReservationsImplTest {

  @Mock private SurgeryReservationRepository reservationRepository;

  @InjectMocks private GetUnconfirmedVisibleSurgeryReservationsImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getUnconfirmedVisible_shouldReturnPagedResponses() {
    Pageable pageable = PageRequest.of(0, 10);

    Contact contact = new Contact();
    contact.setIdContact(300);
    contact.setIdContactString("CT300");
    contact.setName("Bob");
    contact.setSurname("Marley");

    Patient patient = new Patient();
    patient.setIdPatient(3);
    patient.setContact(contact);

    SurgeryReservation reservation = new SurgeryReservation();
    reservation.setIdSurgeryReservation(30);
    reservation.setPatient(patient);
    reservation.setDescription("Unconfirmed Reservation");
    reservation.setFollicularUnits(60);
    reservation.setSurgicalTechnique("Technique Z");
    reservation.setEstimatedDate(LocalDate.now().plusDays(15));
    reservation.setNational(true);
    reservation.setDeposit(BigDecimal.valueOf(250));
    reservation.setRemainingMoney(BigDecimal.valueOf(450));
    reservation.setSurgeryPrice(BigDecimal.valueOf(700));
    reservation.setIsVisible(true);
    reservation.setConfirmed(false);
    reservation.setIsPaid(false);

    Page<SurgeryReservation> page = new PageImpl<>(List.of(reservation), pageable, 1);

    when(reservationRepository.findAllByIsVisibleTrueAndConfirmedFalse(pageable)).thenReturn(page);

    Page<SurgeryReservationResponse> result = service.getUnconfirmedVisible(pageable);

    assertNotNull(result);
    assertEquals(1, result.getTotalElements());
    SurgeryReservationResponse dto = result.getContent().get(0);

    assertEquals(reservation.getIdSurgeryReservation(), dto.getIdSurgeryReservation());
    assertEquals(patient.getIdPatient(), dto.getIdPatient());
    assertEquals(contact.getIdContact(), dto.getIdContact());
    assertEquals(contact.getIdContactString(), dto.getIdContactString());
    assertEquals("Bob Marley", dto.getContactFullName());
    assertEquals(reservation.getDescription(), dto.getDescription());
    assertEquals(reservation.getFollicularUnits(), dto.getFollicularUnits());
    assertEquals(reservation.getSurgicalTechnique(), dto.getSurgicalTechnique());
    assertEquals(reservation.getEstimatedDate(), dto.getEstimatedDate());
    assertEquals(reservation.getNational(), dto.getNational());
    assertEquals(reservation.getDeposit(), dto.getDeposit());
    assertEquals(reservation.getRemainingMoney(), dto.getRemainingMoney());
    assertEquals(reservation.getSurgeryPrice(), dto.getSurgeryPrice());
    assertEquals(reservation.getIsVisible(), dto.getIsVisible());
    assertEquals(reservation.getConfirmed(), dto.getConfirmed());
    assertEquals(reservation.getIsPaid(), dto.getIsPaid());

    verify(reservationRepository).findAllByIsVisibleTrueAndConfirmedFalse(pageable);
  }
}
