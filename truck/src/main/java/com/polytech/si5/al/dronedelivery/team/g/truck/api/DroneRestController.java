package com.polytech.si5.al.dronedelivery.team.g.truck.api;

import com.polytech.si5.al.dronedelivery.team.g.truck.dto.DroneDto;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DroneRestController {

    Logger logger = LoggerFactory.getLogger(DroneRestController.class);

    @Autowired
    DroneRegistration droneRegistration;

    @Autowired
    HttpServletRequest request;

    @PostMapping("/truck-api/delivery/status")
    public void getDeliveryStatus(){
        logger.info("receive start demand");
    }

    @PostMapping("/connect/drone")
    public Long connect(@RequestBody DroneDto droneDto){
        logger.info("new drone connected : " + droneDto.name + " - " + droneDto.host + ":" + droneDto.port);
        return droneRegistration.registerDrone(new Drone(droneDto));
    }
}
