package com.gestion.application.service.contact.impl;

import com.gestion.application.dto.ContactResponse;
import com.gestion.application.exception.ProductInvalidDataException;
import com.gestion.application.mapper.ContactMapper;
import com.gestion.application.model.Contact;
import com.gestion.application.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/** Busca contactos por término único en varios campos. */
@Service
@RequiredArgsConstructor
public class SearchContactsImpl {

  private final ContactRepository repository;
  private final ContactMapper mapper;

  public Page<ContactResponse> search(String term, Pageable pageable) {
    if (term == null || term.trim().isEmpty()) {
      throw new ProductInvalidDataException("El término de búsqueda no puede estar vacío.");
    }

    // Normaliza el término antes de pasarlo a la consulta SQL
    String normalizedTerm = term.trim().toLowerCase();

    // Usamos el repositorio para hacer la búsqueda con paginación
    Page<Contact> contactsPage = repository.searchAllFields(normalizedTerm, pageable);

    // Convertimos los resultados a ContactResponse
    return contactsPage.map(mapper::toResponse);
  }
}
