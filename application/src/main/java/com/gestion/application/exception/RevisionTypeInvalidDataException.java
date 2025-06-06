package com.gestion.application.exception;

public class RevisionTypeInvalidDataException extends RuntimeException {
  public RevisionTypeInvalidDataException() {
    super("Datos inválidos para tipo de revisión.");
  }
}
