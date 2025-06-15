package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class IncomeByPaymentMethodResponseTest {

  @Test
  void testConstructorAndGetters() {
    Map<String, String> incomesMap = new HashMap<>();
    incomesMap.put("Credit Card", "1500.50");
    incomesMap.put("Cash", "700.00");

    LocalDate start = LocalDate.of(2025, 1, 1);
    LocalDate end = LocalDate.of(2025, 6, 30);

    IncomeByPaymentMethodResponse response =
        new IncomeByPaymentMethodResponse(incomesMap, "2200.50", start, end);

    assertEquals(incomesMap, response.getIncomesByPaymentMethod());
    assertEquals("2200.50", response.getTotalIncome());
    assertEquals(start, response.getStartDate());
    assertEquals(end, response.getEndDate());
  }
}
