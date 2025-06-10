package com.gestion.application.service.budget;

import com.gestion.application.dto.BudgetRequest;
import com.gestion.application.dto.BudgetResponse;
import com.gestion.application.mapper.BudgetMapper;
import com.gestion.application.model.Budget;
import com.gestion.application.repository.BudgetRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

  private final BudgetRepository budgetRepository;
  private final BudgetMapper budgetMapper;

  public BudgetService(BudgetRepository budgetRepository, BudgetMapper budgetMapper) {
    this.budgetRepository = budgetRepository;
    this.budgetMapper = budgetMapper;
  }

  public BudgetResponse create(Long contactId, BudgetRequest request) {
    Budget budget = budgetMapper.toEntity(request, contactId);
    budgetRepository.save(budget);
    return budgetMapper.toResponse(budget);
  }

  public List<BudgetResponse> getAllByContactId(Long contactId) {
    return budgetRepository.findByContactId(contactId).stream()
        .map(budgetMapper::toResponse)
        .toList();
  }
}
