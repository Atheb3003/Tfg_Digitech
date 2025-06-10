package com.gestion.application.repository;

import com.gestion.application.model.Patient;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
  Optional<Patient> findByContact_IdContact(Integer idContact);
}
