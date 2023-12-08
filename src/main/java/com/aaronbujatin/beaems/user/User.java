package com.aaronbujatin.beaems.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("users")
public class User {

    @Id
    private String id;
    private String fullName;
    private int age;
    private String gender;
    private LocalDate dob;
    private String permanentAddress;
    private String email;
    private String contactNumber;
    private String highestDegree;
    private String degreeSchool;
    private LocalDate dateGraduated;
    private String position;
    private String team;
    private double payRate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String previousPositionHeld;
    private String previousCompany;
    private String username;
    private String password;
    private String rfid;
    private String role;
    private byte[] image;


}
