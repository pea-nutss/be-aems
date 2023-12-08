package com.aaronbujatin.beaems.registration;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegistrationFeeRepository extends MongoRepository<RegistrationFee, String> {
}
