package com.aaronbujatin.beaems.booking;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {

    List<Booking> findByOrganizerName(String organizerName);

    Boolean existsByEventName(String eventName);

}