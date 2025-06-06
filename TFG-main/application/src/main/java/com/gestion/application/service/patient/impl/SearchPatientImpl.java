package com.gestion.application.service.patient.impl;

import com.gestion.application.dto.ContactResponse;
import com.gestion.application.exception.ProductInvalidDataException;
import com.gestion.application.mapper.ContactMapper;
import com.gestion.application.model.Contact;
import com.gestion.application.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchPatientImpl {

  private final ContactRepository repository;
  private final ContactMapper mapper;

  public Page<ContactResponse> search(String term, Pageable pageable) {
    if (term == null || term.trim().isEmpty()) {
      throw new ProductInvalidDataException("El término de búsqueda no puede estar vacío.");
    }

    String normalizedTerm = term.trim().toLowerCase();

    Page<Contact> contactsPage = repository.searchPatientsFields(normalizedTerm, pageable);

    return contactsPage.map(mapper::toResponse);
  }
}
