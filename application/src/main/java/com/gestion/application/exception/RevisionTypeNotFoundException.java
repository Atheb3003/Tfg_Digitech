package com.gestion.application.exception;

public class RevisionTypeNotFoundException extends RuntimeException {
  public RevisionTypeNotFoundException(Integer id) {
    super("Tipo de revisión con ID " + id + " no encontrado.");
  }
}
