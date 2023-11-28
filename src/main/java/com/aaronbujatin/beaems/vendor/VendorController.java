package com.aaronbujatin.beaems.vendor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/vendors")
@CrossOrigin(origins = "*")
public class VendorController {

    private final VendorService vendorService;

    @PostMapping
    public ResponseEntity<Vendor> save(@RequestBody Vendor vendor){
        return new ResponseEntity<>(vendorService.save(vendor), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable("id") String id){
        return new ResponseEntity<>(vendorService.getVendorById(id), HttpStatus.OK);
    }

    @GetMapping("/event/{organizerName}")
    public ResponseEntity<List<Vendor>> getAllVendors(@PathVariable String organizerName){
        return new ResponseEntity<>(vendorService.getAllVendorByEventName(organizerName), HttpStatus.OK);
    }

}
