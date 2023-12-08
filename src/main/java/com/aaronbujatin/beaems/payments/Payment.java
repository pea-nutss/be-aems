package com.aaronbujatin.beaems.payments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payments")
public class Payment {

    @Id
    private String id;
    private LocalDate date;
    private String eventName;
    private double amount;
    private String selectedPackage;
    private double packageValue;
    private String clientName;
    private String modeOfPayment;
}
