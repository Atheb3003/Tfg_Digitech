package com.gestion.application.service.transactiondetail.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.TransactionDetailInfoResponse;
import com.gestion.application.model.*;
import com.gestion.application.repository.TransactionDetailRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetTransactionDetailInfoImplTest {

  @Mock private TransactionDetailRepository transactionDetailRepository;

  @InjectMocks private GetTransactionDetailInfoImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getDetailInfo_shouldReturnProductDetail() {
    Integer id = 1;
    Product product = new Product();
    product.setName("Prod A");

    TransactionDetail detail = new TransactionDetail();
    detail.setIdDetail(id);
    detail.setProduct(product);
    detail.setQuantity(5);
    detail.setPrice(BigDecimal.valueOf(100));

    when(transactionDetailRepository.findById(id)).thenReturn(Optional.of(detail));

    TransactionDetailInfoResponse resp = service.getDetailInfo(id);

    assertEquals(id, resp.getIdDetail());
    assertEquals("PRODUCT", resp.getType());
    assertEquals("Prod A", resp.getProductName());
    assertEquals(5, resp.getQuantity());
    assertEquals(BigDecimal.valueOf(100), resp.getPrice());
  }

  @Test
  void getDetailInfo_shouldReturnProtocolTreatmentDetail() {
    Integer id = 2;

    Product prod = new Product();
    prod.setName("Prod B");

    ProtocolTreatment pt = new ProtocolTreatment();
    pt.setProduct(prod);
    pt.setPrice(BigDecimal.valueOf(200));
    pt.setIsFinished(true);
    Protocol protocol = new Protocol();
    protocol.setIdProtocol(99);
    pt.setProtocol(protocol);

    TransactionDetail detail = new TransactionDetail();
    detail.setIdDetail(id);
    detail.setProtocolTreatment(pt);

    when(transactionDetailRepository.findById(id)).thenReturn(Optional.of(detail));

    TransactionDetailInfoResponse resp = service.getDetailInfo(id);

    assertEquals(id, resp.getIdDetail());
    assertEquals("PROTOCOL_TREATMENT", resp.getType());
    assertEquals("Prod B", resp.getProductName());
    assertEquals(BigDecimal.valueOf(200), resp.getPrice());
    assertTrue(resp.getProtocolFinished());
    assertEquals(99, resp.getProtocolId());
  }

  @Test
  void getDetailInfo_shouldReturnSurgeryDetail() {
    Integer id = 3;

    SurgeryReservation sr = new SurgeryReservation();
    sr.setSurgicalTechnique("Técnica X");
    sr.setEstimatedDate(LocalDate.of(2025, 6, 15));
    sr.setFollicularUnits(150);
    sr.setSurgeryPrice(BigDecimal.valueOf(3000));
    sr.setDescription("Descripción cirugía");

    TransactionDetail detail = new TransactionDetail();
    detail.setIdDetail(id);
    detail.setSurgeryReservation(sr);

    when(transactionDetailRepository.findById(id)).thenReturn(Optional.of(detail));

    TransactionDetailInfoResponse resp = service.getDetailInfo(id);

    assertEquals(id, resp.getIdDetail());
    assertEquals("SURGERY", resp.getType());
    assertEquals("Técnica X", resp.getSurgicalTechnique());
    assertEquals(LocalDate.of(2025, 6, 15), resp.getEstimatedDate());
    assertEquals(150, resp.getFollicularUnits());
    assertEquals(BigDecimal.valueOf(3000), resp.getSurgeryPrice());
    assertEquals("Descripción cirugía", resp.getSurgeryDescription());
  }

  @Test
  void getDetailInfo_shouldThrowException_whenNotFound() {
    Integer id = 999;

    when(transactionDetailRepository.findById(id)).thenReturn(Optional.empty());

    RuntimeException ex = assertThrows(RuntimeException.class, () -> service.getDetailInfo(id));
    assertTrue(ex.getMessage().contains("No existe TransactionDetail con id " + id));
  }
}
