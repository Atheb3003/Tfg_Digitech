package com.gestion.application.service.surgery.impl;

import com.gestion.application.dto.CreateSurgeryReservationRequest;
import com.gestion.application.exception.PatientNotFoundException;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.PatientRepository;
import com.gestion.application.repository.SurgeryReservationRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateSurgeryReservationImpl {

  private final SurgeryReservationRepository surgeryReservationRepository;
  private final PatientRepository patientRepository;

  public SurgeryReservation create(CreateSurgeryReservationRequest request) {
    var patient =
        patientRepository
            .findById(request.getIdPatient())
            .orElseThrow(() -> new PatientNotFoundException(request.getIdPatient()));

    SurgeryReservation reservation = new SurgeryReservation();
    reservation.setPatient(patient);
    reservation.setDescription(request.getDescription());
    reservation.setFollicularUnits(request.getFollicularUnits());
    reservation.setSurgicalTechnique(request.getSurgicalTechnique());
    reservation.setEstimatedDate(request.getEstimatedDate());
    reservation.setNational(request.getNational());
    reservation.setDeposit(request.getDeposit());
    reservation.setSurgeryPrice(request.getSurgeryPrice());

    // Cálculo de dinero restante e indicador de pago completo
    BigDecimal remaining = reservation.getSurgeryPrice().subtract(reservation.getDeposit());
    reservation.setRemainingMoney(remaining);
    reservation.setIsPaid(remaining.compareTo(BigDecimal.ZERO) <= 0);

    reservation.setIsVisible(request.getIsVisible());

    return surgeryReservationRepository.save(reservation);
  }
}
