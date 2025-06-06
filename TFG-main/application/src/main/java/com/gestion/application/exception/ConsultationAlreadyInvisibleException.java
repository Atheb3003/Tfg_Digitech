package com.gestion.application.exception;

public class ConsultationAlreadyInvisibleException extends RuntimeException {
  public ConsultationAlreadyInvisibleException(Integer id) {
    super("Consulta con ID " + id + " ya está invisible.");
  }
}
