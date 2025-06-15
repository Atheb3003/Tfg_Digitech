package com.gestion.application.service.budget;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.BudgetRequest;
import com.gestion.application.dto.BudgetResponse;
import com.gestion.application.mapper.BudgetMapper;
import com.gestion.application.model.Budget;
import com.gestion.application.repository.BudgetRepository;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class BudgetServiceTest {

  @Mock private BudgetRepository budgetRepository;

  @Mock private BudgetMapper budgetMapper;

  @InjectMocks private BudgetService budgetService;

  private BudgetRequest request;
  private Budget budget;
  private BudgetResponse response;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    request =
        new BudgetRequest(
            "Coordinadora A",
            LocalDate.of(2024, 6, 1),
            LocalDate.of(2024, 7, 1),
            "Injerto Capilar",
            "Minoxidil",
            "FUE",
            6,
            2000,
            3000.0,
            150.0);

    budget = new Budget(); // supondremos que es una entidad JPA común
    response =
        new BudgetResponse(
            1L,
            "Coordinadora A",
            LocalDate.of(2024, 6, 1),
            LocalDate.of(2024, 7, 1),
            "Injerto Capilar",
            "Minoxidil",
            "FUE",
            6,
            2000,
            3000.0,
            150.0);
  }

  @Test
  void testCreate() {
    Long contactId = 1L;

    when(budgetMapper.toEntity(request, contactId)).thenReturn(budget);
    when(budgetRepository.save(budget)).thenReturn(budget); // optional
    when(budgetMapper.toResponse(budget)).thenReturn(response);

    BudgetResponse result = budgetService.create(contactId, request);

    assertNotNull(result);
    assertEquals(response, result);

    verify(budgetMapper).toEntity(request, contactId);
    verify(budgetRepository).save(budget);
    verify(budgetMapper).toResponse(budget);
  }

  @Test
  void testGetAllByContactId() {
    Long contactId = 1L;

    List<Budget> budgets = List.of(budget);
    when(budgetRepository.findByContactId(contactId)).thenReturn(budgets);
    when(budgetMapper.toResponse(budget)).thenReturn(response);

    List<BudgetResponse> result = budgetService.getAllByContactId(contactId);

    assertEquals(1, result.size());
    assertEquals(response, result.get(0));

    verify(budgetRepository).findByContactId(contactId);
    verify(budgetMapper).toResponse(budget);
  }
}
