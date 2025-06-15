package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class DateRangeRequestTest {

  @Test
  void testGettersAndSetters() {
    DateRangeRequest dateRange = new DateRangeRequest();

    LocalDate start = LocalDate.of(2025, 1, 1);
    LocalDate end = LocalDate.of(2025, 12, 31);

    dateRange.setStartDate(start);
    dateRange.setEndDate(end);

    assertEquals(start, dateRange.getStartDate());
    assertEquals(end, dateRange.getEndDate());
  }
}
