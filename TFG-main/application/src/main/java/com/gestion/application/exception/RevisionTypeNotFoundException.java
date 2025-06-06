package com.gestion.application.exception;

public class RevisionTypeNotFoundException extends RuntimeException {
  public RevisionTypeNotFoundException(Integer id) {
    super("Revision type not found: " + id);
  }
}
