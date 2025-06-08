package com.gestion.application.mapper;

import com.gestion.application.dto.BudgetRequest;
import com.gestion.application.dto.BudgetResponse;
import com.gestion.application.model.Budget;
import org.springframework.stereotype.Component;

@Component
public class BudgetMapper {

    public Budget toEntity(BudgetRequest request, Long contactId) {
        Budget budget = new Budget();
        budget.setContactId(contactId);
        budget.setCoordinadora(request.coordinadora());
        budget.setFecha(request.fecha());
        budget.setFechaCirugia(request.fechaCirugia());
        budget.setTratamientoQuirurjico(request.tratamientoQuirurjico());
        budget.setTratamientoPrevio(request.tratamientoPrevio());
        budget.setTecnicaQuirurjica(request.tecnicaQuirurjica());
        budget.setTiempoPrescripcion(request.tiempoPrescripcion());
        budget.setUnidadesFoliculares(request.unidadesFoliculares());
        budget.setPrecioEspecialCirugia(request.precioEspecialCirugia());
        budget.setPrecioKit(request.precioKit());
        return budget;
    }

    public BudgetResponse toResponse(Budget budget) {
        return new BudgetResponse(
                budget.getId(),
                budget.getCoordinadora(),
                budget.getFecha(),
                budget.getFechaCirugia(),
                budget.getTratamientoQuirurjico(),
                budget.getTratamientoPrevio(),
                budget.getTecnicaQuirurjica(),
                budget.getTiempoPrescripcion(),
                budget.getUnidadesFoliculares(),
                budget.getPrecioEspecialCirugia(),
                budget.getPrecioKit()
        );
    }
}
