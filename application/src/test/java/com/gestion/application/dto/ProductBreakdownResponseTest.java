package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ProductBreakdownResponseTest {

  @Test
  void testGettersAndSetters() {
    ProductBreakdownResponse response = new ProductBreakdownResponse();

    response.setStartDate("2025-01-01");
    response.setEndDate("2025-06-30");

    ProductBreakdownItemResponse item1 = new ProductBreakdownItemResponse();
    item1.setType("Medicamento");
    item1.setName("Paracetamol");
    item1.setQuantity(50);
    item1.setIncome("500.00");

    ProductBreakdownItemResponse item2 = new ProductBreakdownItemResponse();
    item2.setType("Suplemento");
    item2.setName("Vitamina C");
    item2.setQuantity(30);
    item2.setIncome("300.00");

    List<ProductBreakdownItemResponse> items = Arrays.asList(item1, item2);
    response.setItems(items);

    assertEquals("2025-01-01", response.getStartDate());
    assertEquals("2025-06-30", response.getEndDate());
    assertEquals(items, response.getItems());
  }
}
