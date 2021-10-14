package com.polytech.si5.al.dronedelivery.team.g.truck.api;

import com.polytech.si5.al.dronedelivery.team.g.truck.components.DronePlanifierBean;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneLauncher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TabletRestController {

    @Autowired
    DroneLauncher droneLauncher;

    Logger logger = LoggerFactory.getLogger(TabletRestController.class);


    @PostMapping("/start/drone/{droneId}/package/{packageId}")
    public void start(@PathVariable Long droneId, @PathVariable Long packageId){
        logger.info("receive start demand");
        droneLauncher.start(droneId, packageId);
    }

}
