package com.gestion.application.service.shipping;

import com.gestion.application.dto.MarkAsShippedRequest;
import com.gestion.application.dto.ShippingRequest;
import com.gestion.application.dto.ShippingResponse;
import com.gestion.application.mapper.ShippingMapper;
import com.gestion.application.model.Shipping;
import com.gestion.application.repository.ShippingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShippingService {

    private final ShippingRepository shippingRepository;

    public ShippingService(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    public ShippingResponse crearShipping(ShippingRequest request) {
        Shipping shipping = ShippingMapper.toEntity(request);
        Shipping saved = shippingRepository.save(shipping);
        return ShippingMapper.toResponse(saved);
    }

    public Page<ShippingResponse> obtenerTodos(Pageable pageable) {
        return shippingRepository.findAllByOrderByIdDesc(pageable)
                .map(ShippingMapper::toResponse);
    }


    public Optional<ShippingResponse> obtenerPorId(Long id) {
        return shippingRepository.findById(id)
                .map(ShippingMapper::toResponse);
    }

    public void eliminar(Long id) {
        shippingRepository.deleteById(id);
    }

    public Optional<ShippingResponse> marcarComoEnviado(Long id, MarkAsShippedRequest request) {
        return shippingRepository.findById(id).map(shipping -> {
            shipping.setEnviado(request.isEnviado());
            shipping.setFechaRealEnvio(request.getFechaRealEnvio());
            shippingRepository.save(shipping);
            return ShippingMapper.toResponse(shipping);
        });
    }

}
