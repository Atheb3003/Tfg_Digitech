package com.gestion.application.service.transaction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.mapper.TransactionMapper;
import com.gestion.application.model.Transaction;
import com.gestion.application.repository.ContactRepository;
import com.gestion.application.repository.TransactionRepository;
import com.gestion.application.service.transaction.impl.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class TransactionServiceTest {

  @Mock private CreateTransactionImpl createImpl;

  @Mock private GetAllTransactionsImpl listImpl;

  @Mock private GetVisibleTransactionsImpl visibleImpl;

  @Mock private GetTransactionsByPatientImpl byPatientImpl;

  @Mock private DeleteTransactionImpl deleteImpl;

  @Mock private MakeTransactionVisibleImpl makeVisibleImpl;

  @Mock private MakeTransactionInvisibleImpl makeInvisibleImpl;

  @Mock private TransactionMapper mapper;

  @Mock private GetTransactionSummariesImpl getSummariesImpl;

  @Mock private TransactionRepository transactionRepository;

  @Mock private ContactRepository contactRepository;

  @Mock private GetTransactionInfoImpl getTransactionInfoImpl;

  @InjectMocks private TransactionService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createTransaction_shouldDelegate() {
    TransactionRequest req = new TransactionRequest();
    TransactionResponse resp = new TransactionResponse();

    when(createImpl.create(req)).thenReturn(resp);

    TransactionResponse result = service.createTransaction(req);

    assertSame(resp, result);
    verify(createImpl).create(req);
  }

  @Test
  void getAllTransactions_shouldDelegate() {
    List<TransactionResponse> list = List.of(new TransactionResponse());

    when(listImpl.getAllTransactions()).thenReturn(list);

    List<TransactionResponse> result = service.getAllTransactions();

    assertSame(list, result);
    verify(listImpl).getAllTransactions();
  }

  @Test
  void getVisibleTransactions_shouldDelegate() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<TransactionResponse> page = Page.empty(pageable);

    when(visibleImpl.getVisibleTransactions(pageable)).thenReturn(page);

    Page<TransactionResponse> result = service.getVisibleTransactions(pageable);

    assertSame(page, result);
    verify(visibleImpl).getVisibleTransactions(pageable);
  }

  @Test
  void getTransactionsByPatient_shouldDelegate() {
    Integer patientId = 1;
    List<TransactionResponse> list = List.of(new TransactionResponse());

    when(byPatientImpl.getTransactionsByPatient(patientId)).thenReturn(list);

    List<TransactionResponse> result = service.getTransactionsByPatient(patientId);

    assertSame(list, result);
    verify(byPatientImpl).getTransactionsByPatient(patientId);
  }

  @Test
  void deleteTransaction_shouldDelegate() {
    Integer id = 1;

    doNothing().when(deleteImpl).deleteTransaction(id);

    service.deleteTransaction(id);

    verify(deleteImpl).deleteTransaction(id);
  }

  @Test
  void makeTransactionInvisible_shouldDelegateAndMap() {
    Integer id = 2;
    Transaction tx = new Transaction();
    TransactionResponse resp = new TransactionResponse();

    when(makeInvisibleImpl.makeInvisible(id)).thenReturn(tx);
    when(mapper.toResponse(tx)).thenReturn(resp);

    TransactionResponse result = service.makeTransactionInvisible(id);

    assertSame(resp, result);
    verify(makeInvisibleImpl).makeInvisible(id);
  }

  @Test
  void getTransactionSummaries_shouldDelegate() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<TransactionSummaryDTO> page = Page.empty(pageable);

    when(getSummariesImpl.execute(pageable)).thenReturn(page);

    Page<TransactionSummaryDTO> result = service.getTransactionSummaries(pageable);

    assertSame(page, result);
    verify(getSummariesImpl).execute(pageable);
  }

  @Test
  void getTransactionSummariesByContact_shouldThrowIfContactNotFound() {
    Integer contactId = 99;
    Pageable pageable = PageRequest.of(0, 10);

    when(contactRepository.findById(contactId)).thenReturn(java.util.Optional.empty());

    assertThrows(
        ContactNotFoundException.class,
        () -> service.getTransactionSummariesByContact(contactId, pageable));

    verify(contactRepository).findById(contactId);
    verifyNoInteractions(transactionRepository);
  }

  @Test
  void searchTransactions_shouldDelegate() {
    String search = "search";
    Pageable pageable = PageRequest.of(0, 10);
    Page<TransactionResponse> page = Page.empty(pageable);

    when(transactionRepository.searchVisibleTransactions(search, pageable)).thenReturn(page);

    Page<TransactionResponse> result = service.searchTransactions(search, pageable);

    assertSame(page, result);
    verify(transactionRepository).searchVisibleTransactions(search, pageable);
  }

  @Test
  void getTransactionInfo_shouldDelegate() {
    Integer id = 1;
    TransactionResponse response = new TransactionResponse();

    when(getTransactionInfoImpl.getTransactionInfo(id)).thenReturn(response);

    TransactionResponse result = service.getTransactionInfo(id);

    assertSame(response, result);
    verify(getTransactionInfoImpl).getTransactionInfo(id);
  }
}
