package com.aaronbujatin.beaems.vendor;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Document("vendors")
public class Vendor {

    @Id
    private String id;
    private String eventNameReference;
    private String vendorType;
    private String companyName;
    private String email;
    private String regionCityZip;
    private String contact;
    private String website;
    private LocalDate contractSigned;
    private LocalDate contractExpired;
    private String emailAddressLine;
    private String paymentStatus;
    private double finalCost;
    private String contractDescription;
    private String organizerName;

}