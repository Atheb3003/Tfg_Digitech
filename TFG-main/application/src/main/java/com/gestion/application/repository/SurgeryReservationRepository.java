package com.gestion.application.repository;

import com.gestion.application.model.SurgeryReservation;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurgeryReservationRepository extends JpaRepository<SurgeryReservation, Integer> {

  List<SurgeryReservation> findByPatient_IdPatient(Integer idPatient);

  Page<SurgeryReservation> findAllByIsVisibleTrue(Pageable pageable);

  Page<SurgeryReservation> findAllByIsVisibleFalse(Pageable pageable);
}
