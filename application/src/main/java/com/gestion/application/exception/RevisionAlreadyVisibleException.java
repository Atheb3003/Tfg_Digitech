package com.gestion.application.exception;

public class RevisionAlreadyVisibleException extends RuntimeException {
  public RevisionAlreadyVisibleException(Integer id) {
    super("Revisión con ID " + id + " ya está visible.");
  }
}
