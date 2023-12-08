package com.aaronbujatin.beaems.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    @PostMapping
    public ResponseEntity<Payment> save(@RequestBody Payment payment){
        return new ResponseEntity<>(paymentRepository.save(payment), HttpStatus.CREATED);
    }
}
