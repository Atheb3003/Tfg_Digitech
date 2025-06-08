package com.gestion.application.service.transaction.impl;

import com.gestion.application.dto.TransactionSummaryDTO;
import com.gestion.application.model.PaymentMethod;
import com.gestion.application.model.Transaction;
import com.gestion.application.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implementación para obtener resúmenes de transacciones, paginados y ordenados.
 */
@Service
@RequiredArgsConstructor
public class GetTransactionSummariesImpl {

    private final TransactionRepository transactionRepository;

    /**
     * Devuelve una página de TransactionSummaryDTO, ordenada según el Pageable proporcionado.
     */
    public Page<TransactionSummaryDTO> execute(Pageable pageable) {
        return transactionRepository.findAll(pageable)
                .map(tx -> {
                    Integer idTx = tx.getIdTransaction();
                    java.time.LocalDate txDate = tx.getTransactionDate();
                    Double amount = tx.getAmount();
                    PaymentMethod paymentMethod = tx.getPaymentMethod();
                    Integer idPatient = tx.getPatient().getIdPatient();
                    String fullName = tx.getPatient().getContact().getName()
                            + " "
                            + tx.getPatient().getContact().getSurname();
                    Integer idContact = tx.getPatient().getContact().getIdContact();
                    return new TransactionSummaryDTO(
                            idTx,
                            txDate,
                            amount,
                            paymentMethod,
                            idPatient,
                            fullName,
                            idContact
                    );
                });
    }
}
