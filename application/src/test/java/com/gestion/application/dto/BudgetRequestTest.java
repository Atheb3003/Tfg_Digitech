package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class BudgetRequestTest {

  @Test
  void testRecordConstructorAndGetters() {
    LocalDate fecha = LocalDate.of(2025, 6, 14);
    LocalDate fechaCirugia = LocalDate.of(2025, 7, 1);

    BudgetRequest request =
        new BudgetRequest(
            "Laura",
            fecha,
            fechaCirugia,
            "Tratamiento X",
            "Tratamiento Y",
            "Técnica Z",
            10,
            5,
            1500.50,
            200.75);

    assertEquals("Laura", request.coordinadora());
    assertEquals(fecha, request.fecha());
    assertEquals(fechaCirugia, request.fechaCirugia());
    assertEquals("Tratamiento X", request.tratamientoQuirurjico());
    assertEquals("Tratamiento Y", request.tratamientoPrevio());
    assertEquals("Técnica Z", request.tecnicaQuirurjica());
    assertEquals(10, request.tiempoPrescripcion());
    assertEquals(5, request.unidadesFoliculares());
    assertEquals(1500.50, request.precioEspecialCirugia());
    assertEquals(200.75, request.precioKit());
  }
}
