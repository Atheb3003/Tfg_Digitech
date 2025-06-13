package com.gestion.application.service.transactiondetail;

import com.gestion.application.dto.*;
import com.gestion.application.mapper.TransactionDetailMapper;
import com.gestion.application.model.PaymentMethod;
import com.gestion.application.model.TransactionDetail;
import com.gestion.application.service.transactiondetail.impl.*;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionDetailService {

  private final CreateTransactionDetailImpl createImpl;
  private final GetAllTransactionDetailsImpl allImpl;
  private final GetVisibleTransactionDetailsImpl visibleImpl;
  private final GetTransactionDetailsByTransactionImpl byTxImpl;
  private final DeleteTransactionDetailImpl deleteImpl;
  private final MakeTransactionDetailVisibleImpl showImpl;
  private final MakeTransactionDetailInvisibleImpl hideImpl;
  private final GetVisibleSoldItemsImpl soldItemsImpl;
  private final TransactionDetailMapper mapper;
  private final GetTotalIncomeImpl getTotalIncomeImpl;
  private final GetIncomeByPaymentMethodImpl getIncomeByPaymentMethodImpl;
  private final GetProductBreakdownImpl getProductBreakdownImpl;
  private final GetTypeBreakdownImpl getTypeBreakdownImpl;
  private final SearchVisibleSoldItemsImpl searchVisibleSoldItemsImpl;
  private final GetTransactionDetailInfoImpl getTransactionDetailInfoImpl;





  public TransactionDetailResponse createDetail(TransactionDetailRequest req) {
    return createImpl.create(req);
  }

  public List<TransactionDetailResponse> getAllDetails() {
    return allImpl.getAll();
  }

  public List<TransactionDetailResponse> getVisibleDetails() {
    return visibleImpl.getVisible();
  }

  public List<TransactionDetailResponse> getDetailsByTransaction(Integer txId) {
    return byTxImpl.getByTransaction(txId);
  }

  public void deleteDetail(Integer id) {
    deleteImpl.deleteDetail(id);
  }

  public TransactionDetailResponse makeDetailVisible(Integer id) {
    TransactionDetail detail = showImpl.setVisible(id);
    return mapper.toResponse(detail);
  }

  public TransactionDetailResponse makeDetailInvisible(Integer id) {
    TransactionDetail detail = hideImpl.setInvisible(id);
    return mapper.toResponse(detail);
  }

  public Page<SoldItemResponse> getVisibleSoldItems(Pageable pageable) {
    return soldItemsImpl.getVisibleSoldItems(pageable);
  }

  public TotalIncomeResponse getTotalIncome(LocalDate startDate, LocalDate endDate) {
    return getTotalIncomeImpl.getTotalIncome(startDate, endDate);
  }

  public IncomeByPaymentMethodResponse getIncomeByPaymentMethod(LocalDate startDate, LocalDate endDate) {
    return getIncomeByPaymentMethodImpl.getIncomeByPaymentMethod(startDate, endDate);
  }

  public ProductBreakdownResponse getProductBreakdown(LocalDate startDate, LocalDate endDate) {
    return getProductBreakdownImpl.getProductBreakdown(startDate, endDate);
  }

  public TypeBreakdownResponse getTypeBreakdown(LocalDate startDate, LocalDate endDate) {
    return getTypeBreakdownImpl.getTypeBreakdown(startDate, endDate);
  }

  public Page<SoldItemResponse> searchVisibleSoldItems(String term, Pageable pageable) {
    return searchVisibleSoldItemsImpl.searchVisibleSoldItems(term, pageable);
  }
  public TransactionDetailInfoResponse getTransactionDetailInfo(Integer id) {
    return getTransactionDetailInfoImpl.getDetailInfo(id);
  }

}
