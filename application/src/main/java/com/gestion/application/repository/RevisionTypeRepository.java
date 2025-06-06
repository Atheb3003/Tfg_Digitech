package com.gestion.application.repository;

import com.gestion.application.model.RevisionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevisionTypeRepository extends JpaRepository<RevisionType, Integer> {}
