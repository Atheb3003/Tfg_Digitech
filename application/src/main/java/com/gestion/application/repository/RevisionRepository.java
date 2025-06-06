package com.gestion.application.repository;

import com.gestion.application.model.Revision;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevisionRepository extends JpaRepository<Revision, Integer> {

  List<Revision> findByPatientIdPatient(Integer idPatient);

  List<Revision> findByPatientIdPatientAndIsVisibleTrue(Integer idPatient);

  List<Revision> findAllByIsVisibleTrue();
}
