package com.gestion.application.service.surgery.impl;

import com.gestion.application.exception.SurgeryReservationNotFoundException;
import com.gestion.application.model.Surgery;
import com.gestion.application.repository.SurgeryRepository;
import com.gestion.application.repository.SurgeryReservationRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateSurgeryImpl {

  private final SurgeryRepository surgeryRepository;
  private final SurgeryReservationRepository reservationRepository;

  public Surgery createSurgery(Integer reservationId, LocalDate date, String observations) {
    var reservation =
        reservationRepository
            .findById(reservationId)
            .orElseThrow(() -> new SurgeryReservationNotFoundException(reservationId));

    Surgery surgery = new Surgery();
    surgery.setDate(date);
    surgery.setObservations(observations);
    surgery.setIsVisible(true);
    surgery.setSurgeryReservation(reservation);

    reservation.setConfirmed(true);
    reservationRepository.save(reservation);

    return surgeryRepository.save(surgery);
  }
}
