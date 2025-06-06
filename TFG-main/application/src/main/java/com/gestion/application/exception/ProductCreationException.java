package com.gestion.application.exception;

/** Se lanza si ocurre un error inesperado al crear el producto. */
public class ProductCreationException extends RuntimeException {
  public ProductCreationException() {
    super("Error inesperado al crear el producto");
  }
}
