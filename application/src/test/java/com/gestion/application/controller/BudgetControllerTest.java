package com.gestion.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.BudgetRequest;
import com.gestion.application.dto.BudgetResponse;
import com.gestion.application.service.budget.BudgetService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

class BudgetControllerTest {

  @Mock private BudgetService budgetService;

  @InjectMocks private BudgetController budgetController;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateBudget() {
    Long contactId = 1L;
    BudgetRequest request =
        new BudgetRequest(
            "Ana",
            LocalDate.now(),
            LocalDate.now().plusDays(5),
            "Tratamiento X",
            "Ninguno",
            "Técnica Y",
            10,
            1200,
            1500.0,
            250.0);

    BudgetResponse expectedResponse =
        new BudgetResponse(
            99L,
            request.coordinadora(),
            request.fecha(),
            request.fechaCirugia(),
            request.tratamientoQuirurjico(),
            request.tratamientoPrevio(),
            request.tecnicaQuirurjica(),
            request.tiempoPrescripcion(),
            request.unidadesFoliculares(),
            request.precioEspecialCirugia(),
            request.precioKit());

    when(budgetService.create(contactId, request)).thenReturn(expectedResponse);

    ResponseEntity<BudgetResponse> response = budgetController.createBudget(contactId, request);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(99L, response.getBody().id());
  }

  @Test
  void testGetBudgetsByContact() {
    Long contactId = 1L;

    BudgetResponse b1 =
        new BudgetResponse(
            1L,
            "Ana",
            LocalDate.now(),
            LocalDate.now().plusDays(1),
            "Trat1",
            "Previo1",
            "Técnica1",
            10,
            1000,
            2000.0,
            300.0);
    BudgetResponse b2 =
        new BudgetResponse(
            2L,
            "Luis",
            LocalDate.now(),
            LocalDate.now().plusDays(2),
            "Trat2",
            "Previo2",
            "Técnica2",
            12,
            1500,
            2500.0,
            350.0);

    when(budgetService.getAllByContactId(contactId)).thenReturn(List.of(b1, b2));

    ResponseEntity<List<BudgetResponse>> response = budgetController.getBudgetsByContact(contactId);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(2, response.getBody().size());
  }
}
