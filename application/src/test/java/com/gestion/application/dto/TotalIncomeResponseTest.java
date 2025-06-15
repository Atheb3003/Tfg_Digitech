package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class TotalIncomeResponseTest {

  @Test
  void testAllArgsConstructorAndGetters() {
    TotalIncomeResponse response =
        new TotalIncomeResponse(5000.75, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 6, 30));

    assertEquals(5000.75, response.getTotalIncome());
    assertEquals(LocalDate.of(2025, 1, 1), response.getStartDate());
    assertEquals(LocalDate.of(2025, 6, 30), response.getEndDate());
  }
}
