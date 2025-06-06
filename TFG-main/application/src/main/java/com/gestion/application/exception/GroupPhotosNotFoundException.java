package com.gestion.application.exception;

public class GroupPhotosNotFoundException extends RuntimeException {
  public GroupPhotosNotFoundException(Integer id) {
    super("No se encontró un grupo de fotos con el ID: " + id);
  }
}
