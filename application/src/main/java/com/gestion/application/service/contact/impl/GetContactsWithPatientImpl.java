package com.gestion.application.service.contact.impl;

import com.gestion.application.dto.ContactResponse;
import com.gestion.application.mapper.ContactMapper;
import com.gestion.application.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetContactsWithPatientImpl {

  private final ContactRepository contactRepository;
  private final ContactMapper contactMapper;

  public Page<ContactResponse> getContactsWithPatient(Pageable pageable) {
    return contactRepository
        .findAllWithPatientOrderByIdDesc(pageable)
        .map(contactMapper::toResponse);
  }
}
