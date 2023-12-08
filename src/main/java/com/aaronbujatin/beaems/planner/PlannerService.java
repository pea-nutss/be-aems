package com.aaronbujatin.beaems.planner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlannerService {

    private final PlannerRepository plannerRepository;

    public Planner saveToDo(Planner planner){
        return plannerRepository.save(planner);
    }

    public Planner getPlannerById(String id){
        return plannerRepository.findById(id).get();
    }

    public List<Planner> getPlannerByOrganizerName(String organizerName){
        return plannerRepository.findByOrganizerName(organizerName);
    }

    public Planner updatePlanner(Planner planner, String id){

        Planner newPlanner = plannerRepository.findById(id).get();
        if(newPlanner != null) {
            newPlanner.setMessage(planner.getMessage());
            newPlanner.setDate(planner.getDate());
            newPlanner.setTime(planner.getTime());
            newPlanner.setLocation(planner.getLocation());

            return plannerRepository.save(newPlanner);
        }

        return null;
    }

    public String deletePlannerById(String id){
        plannerRepository.deleteById(id);
        return "Id : " + id + " was successfully deleted!";
    }


}
