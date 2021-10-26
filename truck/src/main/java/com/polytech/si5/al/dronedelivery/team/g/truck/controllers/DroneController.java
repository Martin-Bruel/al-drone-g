package com.polytech.si5.al.dronedelivery.team.g.truck.controllers;

import com.polytech.si5.al.dronedelivery.team.g.truck.dto.DeliveryStateDto;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DeliveryStateNotifier;
import com.polytech.si5.al.dronedelivery.team.g.truck.repositories.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DroneController {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private DeliveryStateNotifier deliveryStateNotifier;

    @RequestMapping(value="/drones", method= RequestMethod.GET)
    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    @PostMapping(value = "/delivery", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> post(@RequestBody DeliveryStateDto deliveryStateDto) {
        deliveryStateNotifier.updateDeliverySate(deliveryStateDto.getDroneId(), deliveryStateDto.getDeliveryState());
        return new ResponseEntity<String>("OK", HttpStatus.OK);
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
