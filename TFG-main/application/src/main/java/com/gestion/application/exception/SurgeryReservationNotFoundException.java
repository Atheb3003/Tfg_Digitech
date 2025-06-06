package com.gestion.application.exception;

public class SurgeryReservationNotFoundException extends RuntimeException {
  public SurgeryReservationNotFoundException(Integer id) {
    super("No se encontró la reserva de cirugía con ID: " + id);
  }
}
