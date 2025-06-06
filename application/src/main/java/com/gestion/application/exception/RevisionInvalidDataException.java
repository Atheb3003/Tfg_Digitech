package com.gestion.application.exception;

public class RevisionInvalidDataException extends RuntimeException {
  public RevisionInvalidDataException() {
    super("Datos inválidos para crear/actualizar revisión.");
  }
}
