package com.gestion.application.service.transactiondetail.impl;

import com.gestion.application.dto.TransactionDetailInfoResponse;
import com.gestion.application.model.TransactionDetail;
import com.gestion.application.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTransactionDetailInfoImpl {

    private final TransactionDetailRepository transactionDetailRepository;

    public TransactionDetailInfoResponse getDetailInfo(Integer id) {
        TransactionDetail detail = transactionDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe TransactionDetail con id " + id));

        TransactionDetailInfoResponse resp = new TransactionDetailInfoResponse();
        resp.setIdDetail(detail.getIdDetail());

        if (detail.getProduct() != null) {
            resp.setType("PRODUCT");
            resp.setProductName(detail.getProduct().getName());
            resp.setQuantity(detail.getQuantity());
            resp.setPrice(detail.getPrice());
        } else if (detail.getProtocolTreatment() != null) {
            resp.setType("PROTOCOL_TREATMENT");
            var pt = detail.getProtocolTreatment();
            if (pt.getProduct() != null) {
                resp.setProductName(pt.getProduct().getName());
            }
            resp.setPrice(pt.getPrice());
            resp.setProtocolFinished(pt.getIsFinished());
            if (pt.getProtocol() != null) {
                resp.setProtocolId(pt.getProtocol().getIdProtocol());
            }
        } else if (detail.getSurgeryReservation() != null) {
            resp.setType("SURGERY");
            var sr = detail.getSurgeryReservation();
            resp.setSurgicalTechnique(sr.getSurgicalTechnique());
            resp.setEstimatedDate(sr.getEstimatedDate());
            resp.setFollicularUnits(sr.getFollicularUnits());
            resp.setSurgeryPrice(sr.getSurgeryPrice());
            resp.setSurgeryDescription(sr.getDescription());
        }

        return resp;
    }
}
