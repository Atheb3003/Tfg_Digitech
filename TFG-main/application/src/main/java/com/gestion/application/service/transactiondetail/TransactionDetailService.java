package com.gestion.application.service.transactiondetail;

import com.gestion.application.dto.TransactionDetailRequest;
import com.gestion.application.dto.TransactionDetailResponse;
import com.gestion.application.mapper.TransactionDetailMapper;
import com.gestion.application.model.TransactionDetail;
import com.gestion.application.service.transactiondetail.impl.CreateTransactionDetailImpl;
import com.gestion.application.service.transactiondetail.impl.DeleteTransactionDetailImpl;
import com.gestion.application.service.transactiondetail.impl.GetAllTransactionDetailsImpl;
import com.gestion.application.service.transactiondetail.impl.GetTransactionDetailsByTransactionImpl;
import com.gestion.application.service.transactiondetail.impl.GetVisibleTransactionDetailsImpl;
import com.gestion.application.service.transactiondetail.impl.MakeTransactionDetailInvisibleImpl;
import com.gestion.application.service.transactiondetail.impl.MakeTransactionDetailVisibleImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
  private final TransactionDetailMapper mapper;

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
}
