package com.gestion.application.repository;

import com.gestion.application.model.Consultation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {
  List<Consultation> findAllByIsVisibleTrue();

  List<Consultation> findByPatient_IdPatient(Integer patientId);

  List<Consultation> findByContact_IdContact(Integer contactId);

  List<Consultation> findByType_IdType(Integer typeId);
}
