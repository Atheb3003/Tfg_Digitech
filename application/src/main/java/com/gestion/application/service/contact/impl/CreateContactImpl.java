package com.gestion.application.service.contact.impl;

import com.gestion.application.model.Contact;
import com.gestion.application.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateContactImpl {

  private final ContactRepository contactRepository;

  public Contact crearContacto(Contact contacto) {
    return contactRepository.save(contacto);
  }
}
