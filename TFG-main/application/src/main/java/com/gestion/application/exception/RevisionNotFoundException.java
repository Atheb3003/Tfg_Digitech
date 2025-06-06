package com.gestion.application.exception;

public class RevisionNotFoundException extends RuntimeException {
  public RevisionNotFoundException(Integer id) {
    super("Revision not found: " + id);
  }
}
