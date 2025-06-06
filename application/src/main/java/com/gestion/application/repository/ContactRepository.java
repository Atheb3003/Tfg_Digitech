package com.gestion.application.repository;

import com.gestion.application.model.Contact;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

  @Query("SELECT c FROM Contact c WHERE c.patient IS NOT NULL")
  Page<Contact> findAllWithPatient(Pageable pageable);

  @Query("SELECT c FROM Contact c WHERE c.patient IS NULL")
  Page<Contact> findAllWithoutPatient(Pageable pageable);

  /**
   * Busca coincidencias parciales (case-insensitive) en name, surname, nif, telephoneNumber1 o
   * telephoneNumber2.
   */
  @Query(
      """
      SELECT c FROM Contact c
       WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :term, '%'))
          OR LOWER(c.surname) LIKE LOWER(CONCAT('%', :term, '%'))
          OR LOWER(c.nif) LIKE LOWER(CONCAT('%', :term, '%'))
          OR LOWER(c.telephoneNumber1) LIKE LOWER(CONCAT('%', :term, '%'))
          OR LOWER(c.telephoneNumber2) LIKE LOWER(CONCAT('%', :term, '%'))
    """)
  List<Contact> searchAllFields(@Param("term") String term);
}
