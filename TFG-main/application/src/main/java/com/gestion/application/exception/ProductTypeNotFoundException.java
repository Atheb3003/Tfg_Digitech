package com.gestion.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Se lanza cuando no existe un ProductType con el ID indicado. */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductTypeNotFoundException extends RuntimeException {
  public ProductTypeNotFoundException(Integer id) {
    super("No se encontró ningún tipo de producto con el ID '" + id + "'");
  }
}
