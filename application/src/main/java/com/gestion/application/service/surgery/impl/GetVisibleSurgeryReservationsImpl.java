// src/main/java/com/gestion/application/service/surgery/impl/GetVisibleSurgeryReservationsImpl.java
package com.gestion.application.service.surgery.impl;

import com.gestion.application.dto.SurgeryReservationResponse;
import com.gestion.application.repository.SurgeryReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetVisibleSurgeryReservationsImpl {

  private final SurgeryReservationRepository reservationRepository;

  /** Recupera sólo las reservas con isVisible=true, en la página y orden indicados. */
  public Page<SurgeryReservationResponse> getVisible(Pageable pageable) {
    return reservationRepository
        .findAllByIsVisibleTrue(pageable)
        .map(
            reservation -> {
              SurgeryReservationResponse dto = new SurgeryReservationResponse();
              dto.setIdSurgeryReservation(reservation.getIdSurgeryReservation());
              dto.setIdPatient(reservation.getPatient().getIdPatient());
              dto.setIdContact(reservation.getPatient().getContact().getIdContact());
              dto.setIdContactString(reservation.getPatient().getContact().getIdContactString());
              dto.setContactFullName(
                  reservation.getPatient().getContact().getName()
                      + " "
                      + reservation.getPatient().getContact().getSurname());
              dto.setDescription(reservation.getDescription());
              dto.setFollicularUnits(reservation.getFollicularUnits());
              dto.setSurgicalTechnique(reservation.getSurgicalTechnique());
              dto.setEstimatedDate(reservation.getEstimatedDate());
              dto.setNational(reservation.getNational());
              dto.setDeposit(reservation.getDeposit());
              dto.setRemainingMoney(reservation.getRemainingMoney());
              dto.setSurgeryPrice(reservation.getSurgeryPrice());
              dto.setIsVisible(reservation.getIsVisible());
              dto.setConfirmed(reservation.getConfirmed());
              dto.setIsPaid(reservation.getIsPaid());
              return dto;
            });
  }
}
