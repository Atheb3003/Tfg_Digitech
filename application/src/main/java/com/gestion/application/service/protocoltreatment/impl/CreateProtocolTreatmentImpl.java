//// src/main/java/com/gestion/application/service/protocoltreatment/impl/CreateProtocolTreatmentImpl.java
//package com.gestion.application.service.protocoltreatment.impl;
//
//import com.gestion.application.dto.ProtocolTreatmentRequest;
//import com.gestion.application.dto.ProtocolTreatmentResponse;
//import com.gestion.application.exception.ProtocolNotFoundException;
//import com.gestion.application.exception.ProductNotFoundException;
//import com.gestion.application.exception.ProtocolTreatmentCreationException;
//import com.gestion.application.mapper.ProtocolTreatmentMapper;
//import com.gestion.application.model.ProtocolTreatment;
//import com.gestion.application.repository.ProtocolRepository;
//import com.gestion.application.repository.ProductRepository;
//import com.gestion.application.repository.ProtocolTreatmentRepository;
//import com.gestion.application.service.protocoltreatment.ProtocolTreatmentService;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class CreateProtocolTreatmentImpl implements ProtocolTreatmentService {
//
//    private final ProtocolTreatmentRepository repo;
//    private final ProtocolRepository protocolRepo;
//    private final ProductRepository productRepo;
//    private final ProtocolTreatmentMapper mapper;
//
//    @Override @Transactional
//    public ProtocolTreatmentResponse create(ProtocolTreatmentRequest req) {
//        if (req.getProtocolId() == null || req.getProductId() == null) {
//            throw new ProtocolTreatmentCreationException("Protocol and Product are required");
//        }
//        protocolRepo.findById(req.getProtocolId())
//                .orElseThrow(() -> new ProtocolNotFoundException(req.getProtocolId()));
//        productRepo.findById(req.getProductId())
//                .orElseThrow(() -> new ProductNotFoundException(req.getProductId()));
//
//        ProtocolTreatment entity = mapper.toEntity(req);
//        try {
//            ProtocolTreatment saved = repo.save(entity);
//            return mapper.toResponse(saved);
//        } catch (Exception ex) {
//            throw new ProtocolTreatmentCreationException("Error creating ProtocolTreatment");
//        }
//    }
//
//    @Override
//    public List<ProtocolTreatmentResponse> getAll() {
//        return repo.findAll().stream()
//                .map(mapper::toResponse).toList();
//    }
//
//    @Override
//    public List<ProtocolTreatmentResponse> getByProtocol(Integer protocolId) {
//        return repo.findByProtocolId(protocolId).stream()
//                .map(mapper::toResponse).toList();
//    }
//
//    @Override @Transactional
//    public ProtocolTreatmentResponse markFinished(Integer id) {
//        ProtocolTreatment pt = repo.findById(id)
//                .orElseThrow(() -> new ProtocolTreatmentCreationException("Not found: " + id));
//        pt.setIsFinished(true);
//        return mapper.toResponse(repo.save(pt));
//    }
//
//    @Override @Transactional
//    public ProtocolTreatmentResponse markPaid(Integer id) {
//        ProtocolTreatment pt = repo.findById(id)
//                .orElseThrow(() -> new ProtocolTreatmentCreationException("Not found: " + id));
//        pt.setIsPaid(true);
//        return mapper.toResponse(repo.save(pt));
//    }
//
//    @Override @Transactional
//    public void delete(Integer id) {
//        if (!repo.existsById(id)) {
//            throw new ProtocolTreatmentCreationException("Not found: " + id);
//        }
//        repo.deleteById(id);
//    }
//}
