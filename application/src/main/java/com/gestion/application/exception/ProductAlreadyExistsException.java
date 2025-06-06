package com.gestion.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Se lanza cuando ya existe un producto con el mismo nombre. */
@ResponseStatus(HttpStatus.CONFLICT)
public class ProductAlreadyExistsException extends RuntimeException {
  public ProductAlreadyExistsException(String name) {
    super("Ya existe un producto con el nombre '" + name + "'");
  }
}
