package com.gestion.application.service.transactiondetail.impl;

import com.gestion.application.dto.IncomeByPaymentMethodResponse;
import com.gestion.application.model.TransactionDetail;
import com.gestion.application.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetIncomeByPaymentMethodImpl {

    private final TransactionDetailRepository transactionDetailRepository;

    private String formatAmount(double value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
        return df.format(value);
    }

    public IncomeByPaymentMethodResponse getIncomeByPaymentMethod(LocalDate startDate, LocalDate endDate) {
        List<TransactionDetail> all = transactionDetailRepository.findAllByIsVisibleTrue();
        if (all.isEmpty()) {
            return new IncomeByPaymentMethodResponse(Collections.emptyMap(), formatAmount(0.0), startDate, endDate);
        }

        LocalDate minDate = all.stream().map(td -> td.getTransaction().getTransactionDate()).min(LocalDate::compareTo).orElse(LocalDate.now());
        LocalDate maxDate = all.stream().map(td -> td.getTransaction().getTransactionDate()).max(LocalDate::compareTo).orElse(LocalDate.now());
        LocalDate effectiveStart = startDate != null ? startDate : minDate;
        LocalDate effectiveEnd = endDate != null ? endDate : maxDate;

        List<TransactionDetail> filtered = all.stream()
                .filter(td -> {
                    LocalDate txDate = td.getTransaction().getTransactionDate();
                    return !txDate.isBefore(effectiveStart) && !txDate.isAfter(effectiveEnd);
                }).collect(Collectors.toList());

        Map<String, Double> incomeByMethod = filtered.stream()
                .collect(Collectors.groupingBy(
                        td -> td.getTransaction().getPaymentMethod().name(),
                        Collectors.summingDouble(td -> td.getPrice() != null ? td.getPrice().doubleValue() * (td.getQuantity() == null ? 1 : td.getQuantity()) : 0.0)
                ));

        Map<String, String> formatted = new LinkedHashMap<>();
        double total = 0.0;
        for (Map.Entry<String, Double> e : incomeByMethod.entrySet()) {
            formatted.put(e.getKey(), formatAmount(e.getValue()));
            total += e.getValue();
        }

        return new IncomeByPaymentMethodResponse(formatted, formatAmount(total), effectiveStart, effectiveEnd);
    }
}
