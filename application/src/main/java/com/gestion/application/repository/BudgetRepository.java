package com.gestion.application.repository;

import com.gestion.application.model.Budget;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

  List<Budget> findByContactId(Long contactId);
}
