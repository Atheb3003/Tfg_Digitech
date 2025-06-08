package com.gestion.application.exception;

public class RevisionAlreadyVisibleException extends RuntimeException {
  public RevisionAlreadyVisibleException(String message) {
    super(message);
  }
}
