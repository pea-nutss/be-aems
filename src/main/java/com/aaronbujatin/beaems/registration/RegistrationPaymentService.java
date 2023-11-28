package com.aaronbujatin.beaems.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegistrationPaymentService {

    private final RegistrationFeeRepository registrationPaymentRepository;

    public RegistrationFee save(RegistrationFee registrationPayment){
        return registrationPaymentRepository.save(registrationPayment);
    }

}
