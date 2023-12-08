package com.aaronbujatin.beaems.guest;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GuestRepository extends MongoRepository<Guest, String> {

    List<Guest> findByEventNameReferenceAndStatus(String eventName, String status);

    List<Guest> findByEventNameReferenceAndFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String eventName, String firstName, String eventName2, String lastName);

    List<Guest> findByEventNameReference(String eventNameReference);

    Guest findByEmailIgnoreCase(String email);

    Guest findByTableNumberAndEventNameReference(int tableNumber, String eventNameReference);

    boolean existsByTableNumberAndEventNameReference(int tableNumber, String eventNameReference);

}