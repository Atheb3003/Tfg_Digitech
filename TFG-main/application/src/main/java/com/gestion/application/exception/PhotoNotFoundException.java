package com.gestion.application.exception;

public class PhotoNotFoundException extends RuntimeException {
  public PhotoNotFoundException(Integer id) {
    super("No se encontró la foto con ID: " + id);
  }
}
