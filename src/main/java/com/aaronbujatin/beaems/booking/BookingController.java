package com.aaronbujatin.beaems.booking;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> save(@RequestBody Booking booking) throws MessagingException {
        return new ResponseEntity<>(bookingService.save(booking), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") String id){
        return new ResponseEntity<>(bookingService.getBookingById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Booking>> getAllBooking(){
        return new ResponseEntity<>(bookingService.getAllBooking(), HttpStatus.OK);
    }

    @GetMapping("/organizer/{organizerName}")
    public ResponseEntity<List<Booking>> getBookingByOrganizerName(@PathVariable String organizerName){
        return new ResponseEntity<>(bookingService.getBookingByOrganizerName(organizerName), HttpStatus.OK);
    }


}
