package com.gestion.application.exception;

/** Se lanza si hay un error inesperado al actualizar un producto. */
public class ProductUpdateException extends RuntimeException {
  public ProductUpdateException() {
    super("Error al filtrar por tipo");
  }
}
