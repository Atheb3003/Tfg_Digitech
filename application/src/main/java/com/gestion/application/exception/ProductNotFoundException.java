package com.gestion.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Se lanza cuando no existe un producto con el ID indicado. */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(Integer id) {
    super("Producto con ID " + id + " no encontrado");
  }
}
