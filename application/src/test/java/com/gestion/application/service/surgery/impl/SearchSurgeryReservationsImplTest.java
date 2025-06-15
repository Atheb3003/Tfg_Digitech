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

class SearchSurgeryReservationsImplTest {

  @Mock private SurgeryReservationRepository repo;

  @InjectMocks private SearchSurgeryReservationsImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void searchVisibleReservations_shouldReturnPagedDtos() {
    String searchTerm = "search text";
    Pageable pageable = PageRequest.of(0, 10);

    Contact contact = new Contact();
    contact.setIdContact(800);
    contact.setIdContactString("CT800");
    contact.setName("Frank");
    contact.setSurname("Castle");

    Patient patient = new Patient();
    patient.setIdPatient(80);
    patient.setContact(contact);

    SurgeryReservation reservation = new SurgeryReservation();
    reservation.setIdSurgeryReservation(8000);
    reservation.setPatient(patient);
    reservation.setDescription("Reservation desc");
    reservation.setFollicularUnits(150);
    reservation.setSurgicalTechnique("Technique F");
    reservation.setEstimatedDate(LocalDate.now().plusDays(12));
    reservation.setNational(true);
    reservation.setDeposit(BigDecimal.valueOf(500));
    reservation.setRemainingMoney(BigDecimal.valueOf(1000));
    reservation.setSurgeryPrice(BigDecimal.valueOf(1500));
    reservation.setIsVisible(true);
    reservation.setConfirmed(true);
    reservation.setIsPaid(false);

    Page<SurgeryReservation> page = new PageImpl<>(List.of(reservation), pageable, 1);

    when(repo.searchVisibleReservations(searchTerm, pageable)).thenReturn(page);

    Page<SurgeryReservationResponse> result =
        service.searchVisibleReservations(searchTerm, pageable);

    assertNotNull(result);
    assertEquals(1, result.getTotalElements());

    SurgeryReservationResponse dto = result.getContent().get(0);

    assertEquals(reservation.getIdSurgeryReservation(), dto.getIdSurgeryReservation());
    assertEquals(patient.getIdPatient(), dto.getIdPatient());
    assertEquals(contact.getIdContact(), dto.getIdContact());
    assertEquals(contact.getIdContactString(), dto.getIdContactString());
    assertEquals("Frank Castle", dto.getContactFullName());
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

    verify(repo).searchVisibleReservations(searchTerm, pageable);
  }
}
