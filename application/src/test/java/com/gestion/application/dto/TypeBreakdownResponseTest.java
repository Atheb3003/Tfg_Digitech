package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import com.gestion.application.dto.TypeBreakdownResponse.TypeSummary;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TypeBreakdownResponseTest {

  @Test
  void testGettersAndSetters() {
    TypeBreakdownResponse response = new TypeBreakdownResponse();

    response.setStartDate("2025-01-01");
    response.setEndDate("2025-06-30");

    TypeSummary type1 = new TypeSummary();
    type1.setType("Producto");
    type1.setQuantity(100);
    type1.setTotalIncome("5000.00");

    TypeSummary type2 = new TypeSummary();
    type2.setType("Tratamiento");
    type2.setQuantity(50);
    type2.setTotalIncome("7500.00");

    List<TypeSummary> types = Arrays.asList(type1, type2);
    response.setTypes(types);

    assertEquals("2025-01-01", response.getStartDate());
    assertEquals("2025-06-30", response.getEndDate());
    assertEquals(types, response.getTypes());

    // También validar contenido de la lista
    assertEquals("Producto", response.getTypes().get(0).getType());
    assertEquals(100, response.getTypes().get(0).getQuantity());
    assertEquals("5000.00", response.getTypes().get(0).getTotalIncome());
    assertEquals("Tratamiento", response.getTypes().get(1).getType());
    assertEquals(50, response.getTypes().get(1).getQuantity());
    assertEquals("7500.00", response.getTypes().get(1).getTotalIncome());
  }
}
