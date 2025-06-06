package com.gestion.application.exception;

public class ConsultationAlreadyVisibleException extends RuntimeException {
  public ConsultationAlreadyVisibleException(Integer id) {
    super("Consulta con ID " + id + " ya está visible.");
  }
}
