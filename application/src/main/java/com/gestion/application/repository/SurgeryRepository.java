// src/main/java/com/gestion/application/repository/SurgeryRepository.java
package com.gestion.application.repository;

import com.gestion.application.model.Surgery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SurgeryRepository extends JpaRepository<Surgery, Integer> {
    Page<Surgery> findAllByIsVisibleTrue(Pageable pageable);

    @Query("""
      SELECT DISTINCT s
      FROM Surgery s
      JOIN s.surgeryReservation r
      JOIN r.patient         p
      JOIN p.contact         c
      WHERE s.isVisible = true
        AND (
          CAST(s.idSurgery           AS string) LIKE CONCAT('%', :search, '%') OR
          LOWER(c.idContactString)                LIKE LOWER(CONCAT('%', :search, '%')) OR
          LOWER(CONCAT(c.name,' ',c.surname))     LIKE LOWER(CONCAT('%', :search, '%')) OR
          LOWER(r.surgicalTechnique)              LIKE LOWER(CONCAT('%', :search, '%')) OR
          CAST(r.follicularUnits     AS string)   LIKE CONCAT('%', :search, '%') OR
          CAST(r.surgeryPrice        AS string)   LIKE CONCAT('%', :search, '%')
        )
    """)
    Page<Surgery> searchVisibleSurgeries(
            @Param("search")   String search,
            Pageable pageable
    );
}
