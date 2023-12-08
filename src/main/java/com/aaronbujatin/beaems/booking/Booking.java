package com.aaronbujatin.beaems.booking;

import com.aaronbujatin.beaems.payments.Payment;
import com.aaronbujatin.beaems.registration.RegistrationFee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("bookings")
public class Booking {

    @Id
    private String id;
    private String clientName;
    private String clientContactNumber;
    private String email;
    private String groomName;
    private String groomContactNumber;
    private String brideName;
    private String brideContactNumber;
    private String address;
    private LocalDate weddingDate;
    private String weddingType;
    private String weddingTheme;
    private String ceremonyVenue;
    private String receptionVenue;
    private String selectedPackage;
    private RegistrationFee registrationFee;
    private String eventName;
    private String bookingStatus;
    private String bookingSource;
    private LocalDate bookingDate;
    private String organizerName;
    private String paymentStatus;
    private double packageRate;
    private double balance;





}