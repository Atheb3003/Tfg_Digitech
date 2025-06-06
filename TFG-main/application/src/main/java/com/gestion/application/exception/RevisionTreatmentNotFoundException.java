package com.gestion.application.exception;

public class RevisionTreatmentNotFoundException extends RuntimeException {
  public RevisionTreatmentNotFoundException(Integer id) {
    super("Tratamiento de revisión con ID " + id + " no encontrado.");
  }
}
