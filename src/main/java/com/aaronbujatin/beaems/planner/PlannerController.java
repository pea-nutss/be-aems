package com.aaronbujatin.beaems.planner;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/planner")
@CrossOrigin(origins = "*")
public class PlannerController {

    private final PlannerService plannerService;

    @PostMapping
    public ResponseEntity<Planner> savePlanner(@RequestBody Planner planner){
        return new ResponseEntity<>(plannerService.saveToDo(planner), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planner> getPlannerById(@PathVariable String id){
        return new ResponseEntity<>(plannerService.getPlannerById(id), HttpStatus.OK);
    }

    @GetMapping("/organizer/{organizerName}")
    public ResponseEntity<List<Planner>> getAllPlanner(@PathVariable String organizerName){
        return new ResponseEntity<>(plannerService.getPlannerByOrganizerName(organizerName), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Planner> updatePlannerById(@PathVariable String id, @RequestBody Planner planner){
        return new ResponseEntity<>(plannerService.updatePlanner(planner,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlannerById(@PathVariable String id){
        return new ResponseEntity<>(plannerService.deletePlannerById(id), HttpStatus.OK);
    }


}
