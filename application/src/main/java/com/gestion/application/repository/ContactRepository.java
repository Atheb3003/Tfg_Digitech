package com.gestion.application.repository;

import com.gestion.application.model.Contact;
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
          LEFT JOIN c.patient p
          WHERE (
              LOWER(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(c.name, 'á', 'a'), 'é', 'e'), 'í', 'i'), 'ó', 'o'), 'ú', 'u'), 'Á', 'a')) LIKE LOWER(CONCAT('%', :term, '%'))
              OR LOWER(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(c.surname, 'á', 'a'), 'é', 'e'), 'í', 'i'), 'ó', 'o'), 'ú', 'u'), 'Á', 'a')) LIKE LOWER(CONCAT('%', :term, '%'))
              OR LOWER(c.nif) LIKE LOWER(CONCAT('%', :term, '%'))
              OR LOWER(c.telephoneNumber1) LIKE LOWER(CONCAT('%', :term, '%'))
              OR LOWER(c.telephoneNumber2) LIKE LOWER(CONCAT('%', :term, '%'))
              OR LOWER(c.email) LIKE LOWER(CONCAT('%', :term, '%'))
              OR LOWER(c.idContactString) LIKE LOWER(CONCAT('%', :term, '%'))
          )
          AND p IS NULL
          """)
  Page<Contact> searchAllFields(@Param("term") String term, Pageable pageable);

  @Query(
      """
          SELECT c FROM Contact c
          LEFT JOIN c.patient p
          WHERE (
              LOWER(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(c.name, 'á', 'a'), 'é', 'e'), 'í', 'i'), 'ó', 'o'), 'ú', 'u'), 'Á', 'a')) LIKE LOWER(CONCAT('%', :term, '%'))
              OR LOWER(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(c.surname, 'á', 'a'), 'é', 'e'), 'í', 'i'), 'ó', 'o'), 'ú', 'u'), 'Á', 'a')) LIKE LOWER(CONCAT('%', :term, '%'))
              OR LOWER(c.nif) LIKE LOWER(CONCAT('%', :term, '%'))
              OR LOWER(c.telephoneNumber1) LIKE LOWER(CONCAT('%', :term, '%'))
              OR LOWER(c.telephoneNumber2) LIKE LOWER(CONCAT('%', :term, '%'))
              OR LOWER(c.email) LIKE LOWER(CONCAT('%', :term, '%'))
              OR LOWER(c.idContactString) LIKE LOWER(CONCAT('%', :term, '%'))
          )
          AND p IS NOT NULL
          """)
  Page<Contact> searchPatientsFields(@Param("term") String term, Pageable pageable);

  @Query("SELECT c FROM Contact c WHERE c.patient IS NULL ORDER BY c.id DESC")
  Page<Contact> findAllWithoutPatientOrderByIdDesc(Pageable pageable);

  @Query("SELECT c FROM Contact c WHERE c.patient IS NOT NULL ORDER BY c.id DESC")
  Page<Contact> findAllWithPatientOrderByIdDesc(Pageable pageable);
}
