package com.gestion.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Se lanza cuando no hay transacciones para un paciente dado. */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends RuntimeException {
  public TransactionNotFoundException(Integer patientId) {
    super("No se encontró ninguna transacción para el paciente con ID " + patientId);
  }
}
