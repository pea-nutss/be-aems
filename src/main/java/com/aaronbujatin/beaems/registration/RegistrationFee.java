package com.aaronbujatin.beaems.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("registration_fee")
public class RegistrationFee {

    @Id
    private String id;
    private String cardNumber;
    private String fullName;
    private String expirationDate;
    private String cvv;

}
