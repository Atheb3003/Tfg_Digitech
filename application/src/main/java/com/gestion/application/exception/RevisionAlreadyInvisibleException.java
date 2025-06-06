package com.gestion.application.exception;

public class RevisionAlreadyInvisibleException extends RuntimeException {
  public RevisionAlreadyInvisibleException(Integer id) {
    super("Revisión con ID " + id + " ya está invisible.");
  }
}
