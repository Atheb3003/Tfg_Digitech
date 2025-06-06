package com.gestion.application.exception;

public class ContactNotFoundException extends RuntimeException {

  public ContactNotFoundException(Integer id) {
    super("No se encontró un contacto con el ID " + id + ".");
  }

  public ContactNotFoundException() {
    super("Contacto no encontrado");
  }
}
