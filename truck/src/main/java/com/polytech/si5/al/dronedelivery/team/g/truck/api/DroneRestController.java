package com.polytech.si5.al.dronedelivery.team.g.truck.api;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.ConnectionInterface;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DroneRestController {

    Logger logger = LoggerFactory.getLogger(DroneRestController.class);

    @Autowired
    DroneRegistration droneRegistration;

    @PostMapping("/truck-api/delivery/status")
    public void getDeliveryStatus(){
        logger.info("receive start demand");
    }

    @PostMapping("/connect/drone/name/{name}")
    public void connect(@PathVariable String name, @PathVariable String port) {
        logger.info("new drone connected : " + name);
        //droneRegistration.registerDrone(new Drone(host, new ConnectionInterface(host,port)));
    }
}
