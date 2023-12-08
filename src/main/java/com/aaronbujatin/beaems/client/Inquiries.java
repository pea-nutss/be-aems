package com.aaronbujatin.beaems.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Document("inquiries")
public class Inquiries {

    @Id
    private String id;
    private String brideName;
    private String groomName;
    private int guestNumber;
    private String receptionVenue;
    private String selectedPackage;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate startDate;
    private String phone;
    private String message;

}
