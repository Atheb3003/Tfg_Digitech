package com.gestion.application.exception;

public class ProtocolNotFoundException extends RuntimeException {
  public ProtocolNotFoundException(Integer contactId) {
    super("No se encontró protocolo para el contacto con ID: " + contactId);
  }
}
