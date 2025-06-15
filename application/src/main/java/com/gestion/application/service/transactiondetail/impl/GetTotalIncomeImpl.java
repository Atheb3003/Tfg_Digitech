package com.gestion.application.service.transactiondetail.impl;

import com.gestion.application.dto.TotalIncomeResponse;
import com.gestion.application.model.TransactionDetail;
import com.gestion.application.repository.TransactionDetailRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTotalIncomeImpl {

  private final TransactionDetailRepository transactionDetailRepository;

  public TotalIncomeResponse getTotalIncome(LocalDate startDate, LocalDate endDate) {
    List<TransactionDetail> all = transactionDetailRepository.findAllByIsVisibleTrue();
    if (all.isEmpty()) {
      return new TotalIncomeResponse(0.0, startDate, endDate);
    }

    // Buscar fechas si son nulas
    LocalDate minDate =
        all.stream()
            .map(td -> td.getTransaction().getTransactionDate())
            .min(LocalDate::compareTo)
            .orElse(LocalDate.now());
    LocalDate maxDate =
        all.stream()
            .map(td -> td.getTransaction().getTransactionDate())
            .max(LocalDate::compareTo)
            .orElse(LocalDate.now());

    LocalDate effectiveStart = startDate != null ? startDate : minDate;
    LocalDate effectiveEnd = endDate != null ? endDate : maxDate;

    // Filtra los transactionDetails por rango de fechas
    double total =
        all.stream()
            .filter(
                td -> {
                  LocalDate txDate = td.getTransaction().getTransactionDate();
                  return !txDate.isBefore(effectiveStart) && !txDate.isAfter(effectiveEnd);
                })
            .mapToDouble(
                td ->
                    td.getPrice() != null
                        ? td.getPrice().doubleValue()
                            * (td.getQuantity() == null ? 1 : td.getQuantity())
                        : 0.0)
            .sum();

    return new TotalIncomeResponse(total, effectiveStart, effectiveEnd);
  }
}
