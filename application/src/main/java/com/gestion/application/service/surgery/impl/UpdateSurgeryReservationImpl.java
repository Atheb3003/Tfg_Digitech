package com.gestion.application.service.surgery.impl;

import com.gestion.application.dto.SurgeryReservationResponse;
import com.gestion.application.dto.UpdateSurgeryReservationRequest;
import com.gestion.application.exception.SurgeryReservationNotFoundException;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.SurgeryReservationRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateSurgeryReservationImpl {

  private final SurgeryReservationRepository reservationRepository;

  public SurgeryReservationResponse update(Integer id, UpdateSurgeryReservationRequest request) {
    SurgeryReservation reservation =
        reservationRepository
            .findById(id)
            .orElseThrow(() -> new SurgeryReservationNotFoundException(id));

    // Actualiza los campos básicos
    reservation.setDescription(request.getDescription());
    reservation.setFollicularUnits(request.getFollicularUnits());
    reservation.setSurgicalTechnique(request.getSurgicalTechnique());
    reservation.setEstimatedDate(request.getEstimatedDate());
    reservation.setNational(request.getNational());

    // Actualiza depósito y precio
    reservation.setDeposit(request.getDeposit());
    reservation.setSurgeryPrice(request.getSurgeryPrice());

    // Recalcula dinero restante e indicador de pago completo
    BigDecimal remaining = reservation.getSurgeryPrice().subtract(reservation.getDeposit());
    reservation.setRemainingMoney(remaining);
    reservation.setIsPaid(remaining.compareTo(BigDecimal.ZERO) <= 0);

    reservation.setIsVisible(request.getIsVisible());

    // Persiste los cambios
    reservationRepository.save(reservation);

    // Mapea la respuesta
    SurgeryReservationResponse dto = new SurgeryReservationResponse();
    dto.setIdSurgeryReservation(reservation.getIdSurgeryReservation());
    dto.setDescription(reservation.getDescription());
    dto.setFollicularUnits(reservation.getFollicularUnits());
    dto.setSurgicalTechnique(reservation.getSurgicalTechnique());
    dto.setEstimatedDate(reservation.getEstimatedDate());
    dto.setNational(reservation.getNational());
    dto.setDeposit(reservation.getDeposit());
    dto.setSurgeryPrice(reservation.getSurgeryPrice());
    dto.setIsVisible(reservation.getIsVisible());

    // Datos del paciente/contacto
    if (reservation.getPatient() != null) {
      dto.setIdPatient(reservation.getPatient().getIdPatient());
      if (reservation.getPatient().getContact() != null) {
        var contact = reservation.getPatient().getContact();
        dto.setContactFullName(contact.getName() + " " + contact.getSurname());
      }
    }

    dto.setConfirmed(reservation.getConfirmed());
    // ← Nuevos campos
    dto.setRemainingMoney(reservation.getRemainingMoney());
    dto.setIsPaid(reservation.getIsPaid());

    return dto;
  }
}
