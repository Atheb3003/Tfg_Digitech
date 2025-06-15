package com.gestion.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SurgeryReservationAlreadyPaidException extends RuntimeException {
  public SurgeryReservationAlreadyPaidException(Integer id) {
    super("La reserva " + id + " ya está totalmente pagada");
  }
}
