package com.gestion.application.service.contact.impl;

import com.gestion.application.dto.ContactResponse;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.model.Contact;
import com.gestion.application.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class GetContactAllDetailsImpl {

  private final ContactRepository contactRepository;

  public GetContactAllDetailsImpl(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  public ContactResponse getContactById(Integer id) {
    Contact contact =
        contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));

    return mapToResponse(contact);
  }

  private ContactResponse mapToResponse(Contact contact) {
    ContactResponse response = new ContactResponse();
    response.setIdContact(contact.getIdContact());
    response.setName(contact.getName());
    response.setTelephoneNumber2(contact.getTelephoneNumber2());
    response.setDirection(contact.getDirection());
    response.setBirthDate(contact.getBirthDate());
    response.setSurname(contact.getSurname());
    response.setEmail(contact.getEmail());
    response.setTelephoneNumber1(contact.getTelephoneNumber1());
    response.setNif(contact.getNif());
    response.setCountry(contact.getCountry());
    response.setProvince(contact.getProvince());
    response.setTown(contact.getTown());
    response.setOccupation(contact.getOccupation());
    response.setObservations(contact.getObservations());
    response.setIdContactString(contact.getIdContactString());
    return response;
  }
}
