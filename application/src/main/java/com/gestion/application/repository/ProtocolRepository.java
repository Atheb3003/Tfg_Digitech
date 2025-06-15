package com.gestion.application.repository;

import com.gestion.application.model.Protocol;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // <-- ESTE ES EL CORRECTO
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtocolRepository
    extends JpaRepository<Protocol, Integer>, JpaSpecificationExecutor<Protocol> {

  List<Protocol> findAllByContact_IdContact(Integer contactId);

  @Query(
      """
    SELECT DISTINCT p FROM Protocol p
    LEFT JOIN p.contact c
    LEFT JOIN p.treatments t
    LEFT JOIN t.product prod
    LEFT JOIN c.patient patient
    WHERE c.isVisible = true
    AND (
        :search IS NULL OR :search = ''
        OR CAST(patient.id AS string) = :search
        OR LOWER(c.idContactString) = LOWER(:search)
        OR CAST(p.price AS string) = :search
        OR LOWER(prod.name) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(CONCAT(c.name, ' ', c.surname)) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(p.description) LIKE LOWER(CONCAT('%', :search, '%'))
    )
    """)
  Page<Protocol> searchProtocols(@Param("search") String search, Pageable pageable);
}
