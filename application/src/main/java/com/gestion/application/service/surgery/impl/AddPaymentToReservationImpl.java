package com.gestion.application.service.surgery.impl;

import com.gestion.application.exception.SurgeryReservationAlreadyPaidException;
import com.gestion.application.exception.SurgeryReservationNotFoundException;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.repository.SurgeryReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class AddPaymentToReservationImpl {

    private final SurgeryReservationRepository reservationRepository;

    /**
     * Suma el importe al depósito de la reserva, recalcula remainingMoney e isPaid,
     * y devuelve la entidad actualizada.
     */
    public SurgeryReservation addPayment(Integer id, BigDecimal amount) {
        SurgeryReservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new SurgeryReservationNotFoundException(id));

        // Si ya está totalmente pagada, lanzamos excepción
        if (Boolean.TRUE.equals(reservation.getIsPaid())) {
            throw new SurgeryReservationAlreadyPaidException(id);
        }

        // 1) Actualiza depósito
        BigDecimal newDeposit = reservation.getDeposit().add(amount);
        reservation.setDeposit(newDeposit);

        // 2) Recalcula remainingMoney
        BigDecimal remaining = reservation.getSurgeryPrice().subtract(newDeposit);
        reservation.setRemainingMoney(remaining);

        // 3) Si ya no queda nada por pagar, marcamos isPaid = true
        if (remaining.compareTo(BigDecimal.ZERO) <= 0) {
            reservation.setIsPaid(true);
        }

        // 4) Persistimos y devolvemos
        return reservationRepository.save(reservation);
    }
}
