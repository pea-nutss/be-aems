package com.aaronbujatin.beaems.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment save(Payment payment){
        return paymentRepository.insert(payment);
    }
}
