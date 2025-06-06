package com.gestion.application.exception;

public class RevisionTreatmentInvalidDataException extends RuntimeException {
  public RevisionTreatmentInvalidDataException() {
    super("Datos inválidos para tratamiento de revisión.");
  }
}
