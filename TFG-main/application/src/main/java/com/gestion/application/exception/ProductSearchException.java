package com.gestion.application.exception;

/** Se lanza si hay un error inesperado al buscar un producto. */
public class ProductSearchException extends RuntimeException {
  public ProductSearchException() {
    super("Error al buscar el producto");
  }
}
