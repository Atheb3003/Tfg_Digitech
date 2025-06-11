package com.gestion.application.repository;

import com.gestion.application.model.PerformedTreatment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformedTreatmentRepository extends JpaRepository<PerformedTreatment, Integer> {

  List<PerformedTreatment> findByContactIdContactOrderByPerformedDateDesc(Integer contactId);
}
