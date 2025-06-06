package com.gestion.application.exception;

public class ConsultationNotFoundException extends RuntimeException {
  public ConsultationNotFoundException(Integer id) {
    super("Consulta con ID " + id + " no encontrada.");
  }
}
