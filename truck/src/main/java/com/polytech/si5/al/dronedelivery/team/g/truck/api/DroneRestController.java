package com.polytech.si5.al.dronedelivery.team.g.truck.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DroneRestController {

    Logger logger = LoggerFactory.getLogger(DroneRestController.class);

    @PostMapping("/truck-api/delivery/status")
    public void getDeliveryStatus(){
        logger.info("receive start demand");
    }
}
