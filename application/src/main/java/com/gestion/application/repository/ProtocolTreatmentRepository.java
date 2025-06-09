package com.gestion.application.repository;

import com.gestion.application.model.ProtocolTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtocolTreatmentRepository extends JpaRepository<ProtocolTreatment, Integer> {}
