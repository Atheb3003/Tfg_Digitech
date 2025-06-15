package com.gestion.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.gestion.application.dto.BudgetRequest;
import com.gestion.application.dto.BudgetResponse;
import com.gestion.application.model.Budget;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class BudgetMapperTest {

  private final BudgetMapper mapper = new BudgetMapper();

  @Test
  void testToEntity() {
    BudgetRequest request =
        new BudgetRequest(
            "Coordinadora A",
            LocalDate.of(2025, 6, 14),
            LocalDate.of(2025, 7, 1),
            "Tratamiento Quirúrgico X",
            "Tratamiento Previo Y",
            "Técnica Z",
            12,
            100,
            1500.50,
            200.75);
    Long contactId = 99L;

    Budget entity = mapper.toEntity(request, contactId);

    assertEquals(contactId, entity.getContactId());
    assertEquals("Coordinadora A", entity.getCoordinadora());
    assertEquals(LocalDate.of(2025, 6, 14), entity.getFecha());
    assertEquals(LocalDate.of(2025, 7, 1), entity.getFechaCirugia());
    assertEquals("Tratamiento Quirúrgico X", entity.getTratamientoQuirurjico());
    assertEquals("Tratamiento Previo Y", entity.getTratamientoPrevio());
    assertEquals("Técnica Z", entity.getTecnicaQuirurjica());
    assertEquals(12, entity.getTiempoPrescripcion());
    assertEquals(100, entity.getUnidadesFoliculares());
    assertEquals(1500.50, entity.getPrecioEspecialCirugia());
    assertEquals(200.75, entity.getPrecioKit());
  }

  @Test
  void testToResponse() {
    Budget budget = new Budget();
    ;
    budget.setCoordinadora("Coordinadora B");
    budget.setFecha(LocalDate.of(2025, 5, 10));
    budget.setFechaCirugia(LocalDate.of(2025, 6, 10));
    budget.setTratamientoQuirurjico("Tratamiento Q");
    budget.setTratamientoPrevio("Previo T");
    budget.setTecnicaQuirurjica("Técnica R");
    budget.setTiempoPrescripcion(8);
    budget.setUnidadesFoliculares(80);
    budget.setPrecioEspecialCirugia(1200.00);
    budget.setPrecioKit(180.00);

    BudgetResponse response = mapper.toResponse(budget);

    assertEquals("Coordinadora B", response.coordinadora());
    assertEquals(LocalDate.of(2025, 5, 10), response.fecha());
    assertEquals(LocalDate.of(2025, 6, 10), response.fechaCirugia());
    assertEquals("Tratamiento Q", response.tratamientoQuirurjico());
    assertEquals("Previo T", response.tratamientoPrevio());
    assertEquals("Técnica R", response.tecnicaQuirurjica());
    assertEquals(8, response.tiempoPrescripcion());
    assertEquals(80, response.unidadesFoliculares());
    assertEquals(1200.00, response.precioEspecialCirugia());
    assertEquals(180.00, response.precioKit());
  }
}
