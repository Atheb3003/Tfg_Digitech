package com.gestion.application.service.protocoltreatment.impl;

import com.gestion.application.dto.AddTreatmentToProtocolRequest;
import com.gestion.application.model.Product;
import com.gestion.application.model.Protocol;
import com.gestion.application.model.ProtocolTreatment;
import com.gestion.application.repository.ProductRepository;
import com.gestion.application.repository.ProtocolRepository;
import com.gestion.application.repository.ProtocolTreatmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class AddTreatmentToProtocolService {

    private final ProtocolRepository protocolRepository;
    private final ProductRepository productRepository;
    private final ProtocolTreatmentRepository treatmentRepository;

    @Transactional
    public void addTreatment(AddTreatmentToProtocolRequest request) {
        Protocol protocol = protocolRepository.findById(request.getProtocolId())
                .orElseThrow(() -> new RuntimeException("Protocol not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProtocolTreatment treatment = new ProtocolTreatment();
        treatment.setProtocol(protocol);
        treatment.setProduct(product);
        treatment.setIsFinished(false);
        treatment.setIsPaid(false);
        treatment.setDetail(request.getDetail());
        treatment.setPrice(request.getPrice());


        treatmentRepository.save(treatment);


        BigDecimal currentPrice = protocol.getPrice() != null ? protocol.getPrice() : BigDecimal.ZERO;
        protocol.setPrice(currentPrice.add(request.getPrice()));
        protocolRepository.save(protocol);
    }

}