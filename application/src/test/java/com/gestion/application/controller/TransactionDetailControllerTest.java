package com.gestion.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.service.transactiondetail.TransactionDetailService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class TransactionDetailControllerTest {

  @InjectMocks private TransactionDetailController controller;

  @Mock private TransactionDetailService service;

  private TransactionDetailResponse dummyResponse;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    dummyResponse = new TransactionDetailResponse();
    dummyResponse.setIdDetail(1);
    dummyResponse.setTransactionId(10);
    dummyResponse.setProductId(5);
    dummyResponse.setQuantity(2);
    dummyResponse.setPrice(BigDecimal.valueOf(50));
    dummyResponse.setProtocolTreatmentId(null);
    dummyResponse.setSurgeryReservationId(null);
  }

  @Test
  void testCreateDetail() {
    TransactionDetailRequest request = new TransactionDetailRequest();
    when(service.createDetail(request)).thenReturn(dummyResponse);

    var response = controller.createDetail(request);

    assertEquals(201, response.getStatusCodeValue());
    assertEquals("created", response.getBody().getStatus());
    assertEquals(dummyResponse, response.getBody().getData());
  }

  @Test
  void testGetAll() {
    when(service.getAllDetails()).thenReturn(List.of(dummyResponse));

    var response = controller.getAll();

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(1, response.getBody().getData().size());
  }

  @Test
  void testGetVisible() {
    when(service.getVisibleDetails()).thenReturn(List.of(dummyResponse));

    var response = controller.getVisible();

    assertEquals(200, response.getStatusCodeValue());
    assertFalse(response.getBody().getData().isEmpty());
  }

  @Test
  void testGetByTransaction_validId() {
    when(service.getDetailsByTransaction(10)).thenReturn(List.of(dummyResponse));

    var response = controller.getByTransaction("10");

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("success", response.getBody().getStatus());
  }

  @Test
  void testGetByTransaction_invalidId() {
    assertThrows(
        com.gestion.application.exception.IdArgumentTypeException.class,
        () -> controller.getByTransaction("invalid"));
  }

  @Test
  void testDeleteDetail_validId() {
    doNothing().when(service).deleteDetail(1);

    var response = controller.deleteDetail("1");

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("deleted", response.getBody().getStatus());
  }

  @Test
  void testDeleteDetail_invalidId() {
    assertThrows(
        com.gestion.application.exception.IdArgumentTypeException.class,
        () -> controller.deleteDetail("abc"));
  }

  @Test
  void testSetVisible_validId() {
    when(service.makeDetailVisible(1)).thenReturn(dummyResponse);

    var response = controller.setVisible("1");

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("success", response.getBody().getStatus());
  }

  @Test
  void testSetInvisible_validId() {
    when(service.makeDetailInvisible(1)).thenReturn(dummyResponse);

    var response = controller.setInvisible("1");

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(dummyResponse, response.getBody().getData());
  }

  @Test
  void testGetVisibleSoldItems() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<SoldItemResponse> page = new PageImpl<>(List.of(new SoldItemResponse()));
    when(service.getVisibleSoldItems(pageable)).thenReturn(page);

    var response = controller.getVisibleSoldItems(pageable);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("success", response.getBody().getStatus());
  }

  @Test
  void testSearchVisibleSoldItems() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<SoldItemResponse> page = new PageImpl<>(List.of(new SoldItemResponse()));
    when(service.searchVisibleSoldItems("producto", pageable)).thenReturn(page);

    var response = controller.searchVisibleSoldItems("producto", pageable);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("success", response.getBody().getStatus());
  }

  @Test
  void testGetTotalIncome() {
    LocalDate start = LocalDate.of(2024, 1, 1);
    LocalDate end = LocalDate.of(2024, 12, 31);
    TotalIncomeResponse total = new TotalIncomeResponse(1000.0, start, end);

    when(service.getTotalIncome(start, end)).thenReturn(total);

    var response = controller.getTotalIncome(start, end);

    assertEquals(1000.0, response.getTotalIncome());
  }

  @Test
  void testGetIncomeByPaymentMethod() {
    IncomeByPaymentMethodResponse responseMock =
        mock(IncomeByPaymentMethodResponse.class); // CORREGIDO

    when(service.getIncomeByPaymentMethod(null, null)).thenReturn(responseMock);

    var response = controller.getIncomeByPaymentMethod(null, null);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(responseMock, response.getBody());
  }

  @Test
  void testGetProductBreakdown() {
    ProductBreakdownResponse mock = mock(ProductBreakdownResponse.class);
    when(service.getProductBreakdown(null, null)).thenReturn(mock);

    var response = controller.getProductBreakdown(null, null);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(mock, response.getBody());
  }

  @Test
  void testGetTypeBreakdown() {
    TypeBreakdownResponse mock = mock(TypeBreakdownResponse.class);
    when(service.getTypeBreakdown(null, null)).thenReturn(mock);

    var response = controller.getTypeBreakdown(null, null);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(mock, response.getBody());
  }

  @Test
  void testGetTransactionDetailInfo() {
    TransactionDetailInfoResponse detail = new TransactionDetailInfoResponse();
    when(service.getTransactionDetailInfo(1)).thenReturn(detail);

    var response = controller.getTransactionDetailInfo(1);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(detail, response.getBody().getData());
  }
}
