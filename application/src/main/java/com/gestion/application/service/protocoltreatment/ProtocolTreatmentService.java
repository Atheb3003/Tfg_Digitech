package com.gestion.application.service.protocoltreatment;

import com.gestion.application.dto.SuccessfulMarkAsPaidResponse;
import com.gestion.application.service.protocoltreatment.impl.MarkProtocolTreatmentAsPaidImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProtocolTreatmentService {

    private final MarkProtocolTreatmentAsPaidImpl markAsPaidImpl;

    public SuccessfulMarkAsPaidResponse markAsPaid(Integer treatmentId) {
        return markAsPaidImpl.markAsPaid(treatmentId);
    }
}
