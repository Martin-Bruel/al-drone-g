package com.polytech.si5.al.dronedelivery.team.g.truck.api;

import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TabletRestController {

    @Autowired
    DroneLauncher droneLauncher;

    @PostMapping("/start/drone/{droneId}/package/{packageId}")
    public void start(@PathVariable int droneId, @PathVariable int packageId){
        droneLauncher.start(droneId, packageId);
    }

}
