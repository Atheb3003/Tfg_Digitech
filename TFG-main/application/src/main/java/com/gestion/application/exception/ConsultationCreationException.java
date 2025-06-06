package com.gestion.application.exception;

public class ConsultationCreationException extends RuntimeException {
  public ConsultationCreationException() {
    super("Error al crear la consulta.");
  }
}
