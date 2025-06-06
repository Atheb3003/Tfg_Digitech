package com.gestion.application.service.contact.impl;

import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteContactImpl {

  private final ContactRepository contactRepository;

  public void deleteContact(Integer id) {
    if (!contactRepository.existsById(id)) {
      throw new ContactNotFoundException(id);
    }
    contactRepository.deleteById(id);
  }
}
