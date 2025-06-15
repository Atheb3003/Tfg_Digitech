package com.gestion.application.exception;

public class SurgeryReservationCreationException extends RuntimeException {

  public SurgeryReservationCreationException() {
    super("Error al crear reserva de cirugía");
  }

  public SurgeryReservationCreationException(String message) {
    super(message);
  }
}
