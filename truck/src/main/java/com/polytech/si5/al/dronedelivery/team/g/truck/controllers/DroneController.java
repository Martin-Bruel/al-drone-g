package com.polytech.si5.al.dronedelivery.team.g.truck.controllers;

import com.polytech.si5.al.dronedelivery.team.g.truck.dto.DeliveryStateDto;
import com.polytech.si5.al.dronedelivery.team.g.truck.dto.DroneDto;
import com.polytech.si5.al.dronedelivery.team.g.truck.dto.PositionDroneDto;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.enumeration.DeliveryStatus;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.*;
import com.polytech.si5.al.dronedelivery.team.g.truck.repositories.DeliveryRepository;
import com.polytech.si5.al.dronedelivery.team.g.truck.services.DroneService;
import com.polytech.si5.al.dronedelivery.team.g.truck.services.MapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class DroneController {

    @Autowired
    private DroneFinder droneFinder;

    Logger logger = LoggerFactory.getLogger(DroneController.class);

    @Autowired
    DroneRegistration droneRegistration;

    @Autowired
    private DeliveryStateNotifier deliveryStateNotifier;

    @Autowired
    private DroneModifier droneModifier;

    @Autowired
    private MapService mapService;

    @Autowired
    private PositionProvider positionProvider;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @RequestMapping(value="/drones", method= RequestMethod.GET)
    public List<Drone> getAllDrones() {
        return droneFinder.getAllDrones();
    }

    @PostMapping(value = "/delivery", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> post(@RequestBody DeliveryStateDto deliveryStateDto) {
        deliveryStateNotifier.updateDeliverySate(deliveryStateDto.getDroneId(), deliveryStateDto.getDeliveryState(), deliveryStateDto.getDeliveryId());
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    @GetMapping(value="/drones/{id}")
    public Drone getDrone(@PathVariable Long id) {
        try{
            return droneFinder.findDroneById(id);
        }
        catch (IllegalArgumentException iae){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drone with id " + id + " does not exist...");
        }
    }

    @PostMapping("/truck-api/delivery/status")
    public void postDeliveryStatus(){
        logger.info("receive start demand");
    }

    @PostMapping("/truck-api/delivery/{id}/status")
    public DeliveryStatus getDeliveryStatus(@PathVariable Integer id){
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        if(delivery.isPresent()){
            return delivery.get().getDeliveryStatus();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Delivery with id " + id + " does not exist...");
    }


    @PostMapping("/connect/drone")
    public Long connect(@RequestBody DroneDto droneDto){
        logger.info("new drone connected : " + droneDto.name + " - " + droneDto.host + ":" + droneDto.port);
        Long droneId = droneRegistration.registerDrone(new Drone(droneDto));
        Drone drone = droneFinder.findDroneById(droneId);
        mapService.addDrone(drone);
        return droneId;
    }

    @PostMapping("/truck-api/position")
    public void registerPosition(@RequestBody List<PositionDroneDto> positionsDroneDto){
        droneModifier.setPositionsDrones(positionsDroneDto);
    }
}
