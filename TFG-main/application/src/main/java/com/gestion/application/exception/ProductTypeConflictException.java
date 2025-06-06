package com.gestion.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Se lanza cuando se intenta eliminar un ProductType vinculado a transacciones activas. */
@ResponseStatus(HttpStatus.CONFLICT)
public class ProductTypeConflictException extends RuntimeException {
  public ProductTypeConflictException(Integer id) {
    super("El tipo de producto con ID " + id + " está vinculado a transacciones activas");
  }
}
