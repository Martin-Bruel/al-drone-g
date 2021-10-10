package com.polytech.si5.al.dronedelivery.team.g.truck.controllers;

import com.polytech.si5.al.dronedelivery.team.g.truck.components.DroneBean;
import com.polytech.si5.al.dronedelivery.team.g.truck.repositories.DroneRepository;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DroneController {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private DroneBean droneBean;

    @RequestMapping(value="/drones", method= RequestMethod.GET)
    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    @RequestMapping(value="/drones2", method= RequestMethod.GET)
    public List<Drone> getAllDrones2() {
        return droneBean.findAll();
    }

    @GetMapping(value="/drones/{id}")
    public Drone getDrone(@PathVariable int id) throws Exception {
        Optional<Drone> drone=droneRepository.findById(id);
        if(drone.isPresent()){
            return drone.get();
        }
        throw new Exception();
    }
}
