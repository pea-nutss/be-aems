package com.aaronbujatin.beaems.vendor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public Vendor save(Vendor vendorRequest){

        String eventNameReference = vendorRequest.getEventNameReference();
        String vendorType = vendorRequest.getVendorType();
        Vendor vendor = vendorRepository.findByEventNameReferenceAndVendorType(eventNameReference, vendorType);

        if(vendor != null){
            throw new IllegalStateException("Vendor " + vendorType + " is already exist.");
        }


        return vendorRepository.save(vendorRequest);
    }

    public Vendor getVendorById(String id){
        return vendorRepository.findById(id).get();
    }

    public List<Vendor> getAllVendors(){
        return vendorRepository.findAll();
    }

    public List<Vendor> getAllVendorByEventName(String organizerName){
        return vendorRepository.findByOrganizerName(organizerName);
    }

    public Vendor update(String id, Vendor vendor){

        Vendor newVendor = vendorRepository.findById(id).get();

        if(newVendor == null){
            throw new NullPointerException("Cannot find the vendor. Value is null");
        }else {
            newVendor.setEventNameReference(vendor.getEventNameReference());
            newVendor.setCompanyName(vendor.getCompanyName());
            newVendor.setEmail(vendor.getEmail());
            newVendor.setVendorType(vendor.getVendorType());
            newVendor.setWebsite(vendor.getWebsite());

            newVendor.setContact(vendor.getContact());
            newVendor.setPaymentStatus(vendor.getPaymentStatus());

            return vendorRepository.save(newVendor);
        }
    }



}

