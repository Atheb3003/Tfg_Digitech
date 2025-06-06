package com.gestion.application.service.contact.impl;

import com.gestion.application.dto.ContactRequest;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.model.Contact;
import com.gestion.application.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditContactImpl {

  private final ContactRepository contactRepository;

  public Contact editContact(Integer id, ContactRequest request) {
    if (!contactRepository.existsById(id)) {
      throw new ContactNotFoundException(id);
    }

    Contact contacto = contactRepository.findById(id).get();

    contacto.setName(request.getName());
    contacto.setSurname(request.getSurname());
    contacto.setNif(request.getNif());
    contacto.setBirthDate(request.getBirthDate());
    contacto.setOccupation(request.getOccupation());
    contacto.setCountry(request.getCountry());
    contacto.setProvince(request.getProvince());
    contacto.setTown(request.getTown());
    contacto.setDirection(request.getDirection());
    contacto.setTelephoneNumber1(request.getTelephoneNumber1());
    contacto.setTelephoneNumber2(request.getTelephoneNumber2());
    contacto.setEmail(request.getEmail());
    contacto.setObservations(request.getObservations());
    contacto.setIsVisible(request.getIsVisible());

    return contactRepository.save(contacto);
  }
}
