package com.gestion.application.controller;

import com.gestion.application.dto.BudgetRequest;
import com.gestion.application.dto.BudgetResponse;
import com.gestion.application.service.budget.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts/{contactId}/budgets")

public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<BudgetResponse> createBudget(
            @PathVariable Long contactId,
            @RequestBody BudgetRequest request) {

        BudgetResponse created = budgetService.create(contactId, request);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<BudgetResponse>> getBudgetsByContact(
            @PathVariable Long contactId) {

        List<BudgetResponse> budgets = budgetService.getAllByContactId(contactId);
        return ResponseEntity.ok(budgets);
    }
}
