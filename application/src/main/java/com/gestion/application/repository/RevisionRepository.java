package com.gestion.application.repository;

import com.gestion.application.model.Revision;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para entidad Revision. Incluye métodos para consulta por isVisible, contacto y
 * tipo.
 */
public interface RevisionRepository extends JpaRepository<Revision, Integer> {

  /** Devuelve todas las revisiones con isVisible = true */
  List<Revision> findAllByIsVisibleTrue();

  /** Devuelve todas las revisiones cuyo contact.idContact = contactId */
  List<Revision> findByContact_IdContact(Integer contactId);

  /** Devuelve todas las revisiones cuyo type.idRevisionType = typeId */
  List<Revision> findByType_IdRevisionType(Integer typeId);
}
