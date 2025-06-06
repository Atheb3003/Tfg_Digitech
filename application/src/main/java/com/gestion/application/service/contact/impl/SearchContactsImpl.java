package com.gestion.application.service.contact.impl;

import com.gestion.application.dto.ContactResponse;
import com.gestion.application.exception.ProductInvalidDataException;
import com.gestion.application.mapper.ContactMapper;
import com.gestion.application.model.Contact;
import com.gestion.application.repository.ContactRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Busca contactos por término único en varios campos. */
@Service
@RequiredArgsConstructor
public class SearchContactsImpl {

  private final ContactRepository repository;
  private final ContactMapper mapper;

  public List<ContactResponse> search(String term) {
    if (term == null || term.trim().isEmpty()) {
      throw new ProductInvalidDataException("El término de búsqueda no puede estar vacío.");
    }
    List<Contact> found = repository.searchAllFields(term.trim());
    return found.stream().map(mapper::toResponse).collect(Collectors.toList());
  }
}
