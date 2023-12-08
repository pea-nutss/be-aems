package com.aaronbujatin.beaems.guest;

import com.google.zxing.WriterException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/guests")
@CrossOrigin(origins = "*")
public class GuestController {

    private final GuestService guestService;

    @PostMapping()
    public ResponseEntity<Guest> saveGuest(@RequestBody Guest guest) throws IOException, WriterException, MessagingException {
        return new ResponseEntity<>(guestService.saveGuest(guest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable("id") String id){
        return new ResponseEntity<>(guestService.getGuestById(id), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Guest>> getAllGuestByStatus(@RequestParam String eventName, @RequestParam String status){

        return new ResponseEntity<>(guestService.getAllByEventNameAndStatus(eventName,status), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Guest>> getAllGuest(){
        return new ResponseEntity<>(guestService.getAllGuest(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Guest>> searchGuests(@RequestParam String eventName, @RequestParam String searchQuery) {
        List<Guest> searchResults = guestService.searchGuestsByFirstNameOrLastName(eventName,searchQuery);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

    @GetMapping("/event")
    public ResponseEntity<List<Guest>> getAllByEventNameReference(@RequestParam String eventName){
        List<Guest> guestsByEventNameReferenceResult = guestService.getAllByEventNameReference(eventName);
        return new ResponseEntity<>(guestsByEventNameReferenceResult, HttpStatus.OK);
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<String> confirmAttendance(@RequestParam("token") String confirmationToken){
        return new ResponseEntity<>(guestService.confirmEmail(confirmationToken), HttpStatus.OK);
    }


}