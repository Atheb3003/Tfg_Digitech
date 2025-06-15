package com.gestion.application.service.transactiondetail;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.mapper.TransactionDetailMapper;
import com.gestion.application.model.TransactionDetail;
import com.gestion.application.service.transactiondetail.impl.*;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class TransactionDetailServiceTest {

  @Mock private CreateTransactionDetailImpl createImpl;

  @Mock private GetAllTransactionDetailsImpl allImpl;

  @Mock private GetVisibleTransactionDetailsImpl visibleImpl;

  @Mock private GetTransactionDetailsByTransactionImpl byTxImpl;

  @Mock private DeleteTransactionDetailImpl deleteImpl;

  @Mock private MakeTransactionDetailVisibleImpl showImpl;

  @Mock private MakeTransactionDetailInvisibleImpl hideImpl;

  @Mock private GetVisibleSoldItemsImpl soldItemsImpl;

  @Mock private TransactionDetailMapper mapper;

  @Mock private GetTotalIncomeImpl getTotalIncomeImpl;

  @Mock private GetIncomeByPaymentMethodImpl getIncomeByPaymentMethodImpl;

  @Mock private GetProductBreakdownImpl getProductBreakdownImpl;

  @Mock private GetTypeBreakdownImpl getTypeBreakdownImpl;

  @Mock private SearchVisibleSoldItemsImpl searchVisibleSoldItemsImpl;

  @Mock private GetTransactionDetailInfoImpl getTransactionDetailInfoImpl;

  @InjectMocks private TransactionDetailService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createDetail_delegatesToCreateImpl() {
    TransactionDetailRequest req = new TransactionDetailRequest();
    TransactionDetailResponse resp = new TransactionDetailResponse();
    when(createImpl.create(req)).thenReturn(resp);

    TransactionDetailResponse result = service.createDetail(req);

    assertEquals(resp, result);
    verify(createImpl).create(req);
  }

  @Test
  void getAllDetails_delegatesToAllImpl() {
    List<TransactionDetailResponse> list = List.of(new TransactionDetailResponse());
    when(allImpl.getAll()).thenReturn(list);

    List<TransactionDetailResponse> result = service.getAllDetails();

    assertEquals(list, result);
    verify(allImpl).getAll();
  }

  @Test
  void getVisibleDetails_delegatesToVisibleImpl() {
    List<TransactionDetailResponse> list = List.of(new TransactionDetailResponse());
    when(visibleImpl.getVisible()).thenReturn(list);

    List<TransactionDetailResponse> result = service.getVisibleDetails();

    assertEquals(list, result);
    verify(visibleImpl).getVisible();
  }

  @Test
  void getDetailsByTransaction_delegatesToByTxImpl() {
    int txId = 5;
    List<TransactionDetailResponse> list = List.of(new TransactionDetailResponse());
    when(byTxImpl.getByTransaction(txId)).thenReturn(list);

    List<TransactionDetailResponse> result = service.getDetailsByTransaction(txId);

    assertEquals(list, result);
    verify(byTxImpl).getByTransaction(txId);
  }

  @Test
  void deleteDetail_delegatesToDeleteImpl() {
    int id = 10;
    doNothing().when(deleteImpl).deleteDetail(id);

    service.deleteDetail(id);

    verify(deleteImpl).deleteDetail(id);
  }

  @Test
  void makeDetailVisible_delegatesAndMaps() {
    int id = 20;
    TransactionDetail detail = new TransactionDetail();
    TransactionDetailResponse resp = new TransactionDetailResponse();
    when(showImpl.setVisible(id)).thenReturn(detail);
    when(mapper.toResponse(detail)).thenReturn(resp);

    TransactionDetailResponse result = service.makeDetailVisible(id);

    assertEquals(resp, result);
    verify(showImpl).setVisible(id);
    verify(mapper).toResponse(detail);
  }

  @Test
  void makeDetailInvisible_delegatesAndMaps() {
    int id = 30;
    TransactionDetail detail = new TransactionDetail();
    TransactionDetailResponse resp = new TransactionDetailResponse();
    when(hideImpl.setInvisible(id)).thenReturn(detail);
    when(mapper.toResponse(detail)).thenReturn(resp);

    TransactionDetailResponse result = service.makeDetailInvisible(id);

    assertEquals(resp, result);
    verify(hideImpl).setInvisible(id);
    verify(mapper).toResponse(detail);
  }

  @Test
  void getVisibleSoldItems_delegatesToSoldItemsImpl() {
    Pageable pageable = Pageable.unpaged();
    Page<SoldItemResponse> page = Page.empty();
    when(soldItemsImpl.getVisibleSoldItems(pageable)).thenReturn(page);

    Page<SoldItemResponse> result = service.getVisibleSoldItems(pageable);

    assertEquals(page, result);
    verify(soldItemsImpl).getVisibleSoldItems(pageable);
  }

  @Test
  void getTotalIncome_delegatesToGetTotalIncomeImpl() {
    LocalDate start = LocalDate.now();
    LocalDate end = LocalDate.now();
    TotalIncomeResponse resp = new TotalIncomeResponse(0.0, start, end);
    when(getTotalIncomeImpl.getTotalIncome(start, end)).thenReturn(resp);

    TotalIncomeResponse result = service.getTotalIncome(start, end);

    assertEquals(resp, result);
    verify(getTotalIncomeImpl).getTotalIncome(start, end);
  }

  @Test
  void getIncomeByPaymentMethod_delegatesToGetIncomeByPaymentMethodImpl() {
    LocalDate start = LocalDate.now();
    LocalDate end = LocalDate.now();

    IncomeByPaymentMethodResponse result = service.getIncomeByPaymentMethod(start, end);

    verify(getIncomeByPaymentMethodImpl).getIncomeByPaymentMethod(start, end);
  }

  @Test
  void getProductBreakdown_delegatesToGetProductBreakdownImpl() {
    LocalDate start = LocalDate.now();
    LocalDate end = LocalDate.now();
    ProductBreakdownResponse resp = new ProductBreakdownResponse();
    when(getProductBreakdownImpl.getProductBreakdown(start, end)).thenReturn(resp);

    ProductBreakdownResponse result = service.getProductBreakdown(start, end);

    assertEquals(resp, result);
    verify(getProductBreakdownImpl).getProductBreakdown(start, end);
  }

  @Test
  void getTypeBreakdown_delegatesToGetTypeBreakdownImpl() {
    LocalDate start = LocalDate.now();
    LocalDate end = LocalDate.now();
    TypeBreakdownResponse resp = new TypeBreakdownResponse();
    when(getTypeBreakdownImpl.getTypeBreakdown(start, end)).thenReturn(resp);

    TypeBreakdownResponse result = service.getTypeBreakdown(start, end);

    assertEquals(resp, result);
    verify(getTypeBreakdownImpl).getTypeBreakdown(start, end);
  }

  @Test
  void searchVisibleSoldItems_delegatesToSearchVisibleSoldItemsImpl() {
    String term = "term";
    Pageable pageable = Pageable.unpaged();
    Page<SoldItemResponse> page = Page.empty();
    when(searchVisibleSoldItemsImpl.searchVisibleSoldItems(term, pageable)).thenReturn(page);

    Page<SoldItemResponse> result = service.searchVisibleSoldItems(term, pageable);

    assertEquals(page, result);
    verify(searchVisibleSoldItemsImpl).searchVisibleSoldItems(term, pageable);
  }

  @Test
  void getTransactionDetailInfo_delegatesToGetTransactionDetailInfoImpl() {
    int id = 5;
    TransactionDetailInfoResponse resp = new TransactionDetailInfoResponse();
    when(getTransactionDetailInfoImpl.getDetailInfo(id)).thenReturn(resp);

    TransactionDetailInfoResponse result = service.getTransactionDetailInfo(id);

    assertEquals(resp, result);
    verify(getTransactionDetailInfoImpl).getDetailInfo(id);
  }
}
