package com.aaronbujatin.beaems.planner;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlannerRepository extends MongoRepository<Planner, String> {

    List<Planner> findByOrganizerName(String organizerName);
}
