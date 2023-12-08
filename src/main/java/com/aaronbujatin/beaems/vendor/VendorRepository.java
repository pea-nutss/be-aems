package com.aaronbujatin.beaems.vendor;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VendorRepository extends MongoRepository<Vendor, String> {

    List<Vendor> findByOrganizerName(String organizerName);

    Vendor findByEventNameReferenceAndVendorType(String eventNameReference, String vendorType);
}
