package com.gestion.application.repository;

import com.gestion.application.model.SurgeryReservation;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SurgeryReservationRepository extends JpaRepository<SurgeryReservation, Integer> {

  List<SurgeryReservation> findByPatient_IdPatient(Integer idPatient);

  Page<SurgeryReservation> findAllByIsVisibleTrue(Pageable pageable);

  Page<SurgeryReservation> findAllByIsVisibleFalse(Pageable pageable);

  Page<SurgeryReservation> findAllByIsVisibleTrueAndConfirmedFalse(Pageable pageable);

  @Query(
      """
    SELECT DISTINCT r
    FROM SurgeryReservation r
    JOIN r.patient p
    JOIN p.contact c
    WHERE r.isVisible = true
      AND (
        CAST(r.idSurgeryReservation AS string)   LIKE CONCAT('%', :search, '%') OR
        LOWER(CONCAT(c.name, ' ', c.surname))     LIKE LOWER(CONCAT('%', :search, '%')) OR
        LOWER(c.idContactString)                  LIKE LOWER(CONCAT('%', :search, '%')) OR
        CAST(r.estimatedDate AS string)           LIKE CONCAT('%', :search, '%')
      )
  """)
  Page<SurgeryReservation> searchVisibleReservations(
      @Param("search") String search, Pageable pageable);
}
