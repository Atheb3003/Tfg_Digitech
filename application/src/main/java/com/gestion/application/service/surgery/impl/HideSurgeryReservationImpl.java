package com.gestion.application.service.surgery.impl;

import com.gestion.application.exception.SurgeryReservationNotFoundException;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.SurgeryReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HideSurgeryReservationImpl {

  private final SurgeryReservationRepository reservationRepository;

  public void hide(Integer id) {
    SurgeryReservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new SurgeryReservationNotFoundException(id));

    reservation.setIsVisible(false);
    reservationRepository.save(reservation);
  }
}
