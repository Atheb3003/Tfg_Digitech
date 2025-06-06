package com.gestion.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Se lanza cuando no existe un paciente con el ID indicado. */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientNotFoundException extends RuntimeException {
  public PatientNotFoundException(Integer id) {
    super("No se encontró ningún paciente con el ID " + id);
  }
}
