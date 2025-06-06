package com.gestion.application.mapper;

import com.gestion.application.dto.ContactRequest;
import com.gestion.application.dto.ContactResponse;
import com.gestion.application.model.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

  public Contact toEntity(ContactRequest dto) {
    Contact contact = new Contact();
    contact.setName(dto.getName());
    contact.setSurname(dto.getSurname());
    contact.setNif(dto.getNif());
    contact.setBirthDate(dto.getBirthDate());
    contact.setOccupation(dto.getOccupation());
    contact.setCountry(dto.getCountry());
    contact.setProvince(dto.getProvince());
    contact.setTown(dto.getTown());
    contact.setDirection(dto.getDirection());
    contact.setTelephoneNumber1(dto.getTelephoneNumber1());
    contact.setTelephoneNumber2(dto.getTelephoneNumber2());
    contact.setEmail(dto.getEmail());
    contact.setObservations(dto.getObservations());
    contact.setIsVisible(true);
    return contact;
  }

  public ContactResponse toResponse(Contact contact) {
    ContactResponse dto = new ContactResponse();
    dto.setIdContact(contact.getIdContact());
    dto.setName(contact.getName());
    dto.setSurname(contact.getSurname());
    dto.setNif(contact.getNif());
    dto.setBirthDate(contact.getBirthDate());
    dto.setOccupation(contact.getOccupation());
    dto.setCountry(contact.getCountry());
    dto.setProvince(contact.getProvince());
    dto.setTown(contact.getTown());
    dto.setDirection(contact.getDirection());
    dto.setTelephoneNumber1(contact.getTelephoneNumber1());
    dto.setTelephoneNumber2(contact.getTelephoneNumber2());
    dto.setEmail(contact.getEmail());
    dto.setObservations(contact.getObservations());
    dto.setIsVisible(contact.getIsVisible());
    return dto;
  }
}
