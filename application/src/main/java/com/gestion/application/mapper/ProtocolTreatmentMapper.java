// src/main/java/com/gestion/application/mapper/ProtocolTreatmentMapper.java
package com.gestion.application.mapper;

import com.gestion.application.dto.ProtocolTreatmentRequest;
import com.gestion.application.dto.ProtocolTreatmentResponse;
import com.gestion.application.model.Protocol;
import com.gestion.application.model.Product;
import com.gestion.application.model.ProtocolTreatment;
import org.springframework.stereotype.Component;

@Component
public class ProtocolTreatmentMapper {

    public ProtocolTreatment toEntity(ProtocolTreatmentRequest dto) {
        ProtocolTreatment pt = new ProtocolTreatment();

        if (dto.getProtocolId() != null) {
            Protocol protocol = new Protocol();
            protocol.setIdProtocol(dto.getProtocolId());
            pt.setProtocol(protocol);
        }

        if (dto.getProductId() != null) {
            Product product = new Product();
            product.setIdProduct(dto.getProductId());
            pt.setProduct(product);
        }

        pt.setIsFinished(dto.getIsFinished() != null ? dto.getIsFinished() : Boolean.FALSE);
        pt.setIsPaid(dto.getIsPaid() != null ? dto.getIsPaid() : Boolean.FALSE);

        return pt;
    }

//    public ProtocolTreatmentResponse toResponse(ProtocolTreatment entity) {
//        ProtocolTreatmentResponse dto = new ProtocolTreatmentResponse();
//
//        dto.setId(entity.getId());
//        dto.setProtocolId(entity.getProtocol() != null ? entity.getProtocol().getIdProtocol() : null);
//        dto.setProductId(entity.getProduct() != null ? entity.getProduct().getIdProduct() : null);
//        dto.setIsFinished(entity.getIsFinished());
//        dto.setIsPaid(entity.getIsPaid());
//
//        return dto;
    }

