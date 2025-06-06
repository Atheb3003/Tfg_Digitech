package com.gestion.application.repository;

import com.gestion.application.model.RevisionTreatment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevisionTreatmentRepository extends JpaRepository<RevisionTreatment, Integer> {
  List<RevisionTreatment> findByRevision_IdRevision(Integer revisionId);
}
