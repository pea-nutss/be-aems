package com.aaronbujatin.beaems.client;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiriesRepository extends MongoRepository<Inquiries, String> {
}
