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

class GetAllSurgeryReservationsImplTest {

  @Mock private SurgeryReservationRepository reservationRepository;

  @InjectMocks private GetAllSurgeryReservationsImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getAllVisible_shouldReturnPagedResponses() {
    Pageable pageable = PageRequest.of(0, 10);

    Contact contact = new Contact();
    contact.setIdContact(200);
    contact.setName("Alice");
    contact.setSurname("Smith");

    Patient patient = new Patient();
    patient.setIdPatient(2);
    patient.setContact(contact);

    SurgeryReservation reservation = new SurgeryReservation();
    reservation.setIdSurgeryReservation(20);
    reservation.setPatient(patient);
    reservation.setDescription("Visible Reservation");
    reservation.setFollicularUnits(70);
    reservation.setSurgicalTechnique("Technique X");
    reservation.setEstimatedDate(LocalDate.now().plusDays(7));
    reservation.setNational(false);
    reservation.setDeposit(BigDecimal.valueOf(300));
    reservation.setSurgeryPrice(BigDecimal.valueOf(700));
    reservation.setIsVisible(true);
    reservation.setConfirmed(false);
    reservation.setRemainingMoney(BigDecimal.valueOf(400));
    reservation.setIsPaid(false);

    Page<SurgeryReservation> page = new PageImpl<>(List.of(reservation), pageable, 1);

    when(reservationRepository.findAllByIsVisibleTrue(pageable)).thenReturn(page);

    Page<SurgeryReservationResponse> result = service.getAllVisible(pageable);

    assertNotNull(result);
    assertEquals(1, result.getTotalElements());
    SurgeryReservationResponse dto = result.getContent().get(0);

    assertEquals(reservation.getIdSurgeryReservation(), dto.getIdSurgeryReservation());
    assertEquals(patient.getIdPatient(), dto.getIdPatient());
    assertEquals(contact.getIdContact(), dto.getIdContact());
    assertEquals("Alice Smith", dto.getContactFullName());
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

    verify(reservationRepository).findAllByIsVisibleTrue(pageable);
  }
}
