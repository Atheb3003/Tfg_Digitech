package com.gestion.application.exception;

public class SurgeryReservationInvalidDataException extends RuntimeException {

  public SurgeryReservationInvalidDataException() {
    super("Datos inválidos para reserva de cirugía");
  }

  public SurgeryReservationInvalidDataException(String message) {
    super(message);
  }
}
