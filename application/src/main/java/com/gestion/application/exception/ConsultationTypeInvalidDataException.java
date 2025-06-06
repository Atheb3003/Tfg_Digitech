package com.gestion.application.exception;

public class ConsultationTypeInvalidDataException extends RuntimeException {
  public ConsultationTypeInvalidDataException() {
    super("Datos inválidos para tipo de consulta.");
  }
}
