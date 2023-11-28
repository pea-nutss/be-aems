package com.aaronbujatin.beaems.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class InquiriesController {


    private final InquiriesService userService;

    @PostMapping
    public ResponseEntity<Inquiries> save(@RequestBody Inquiries user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inquiries> getUserById(@PathVariable("id") String id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Inquiries>> getAllUser(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") String id) {
        return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
    }

}
