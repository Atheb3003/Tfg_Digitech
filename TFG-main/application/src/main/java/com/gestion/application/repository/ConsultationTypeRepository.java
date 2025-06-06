package com.gestion.application.repository;

import com.gestion.application.model.ConsultationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationTypeRepository extends JpaRepository<ConsultationType, Integer> {}
