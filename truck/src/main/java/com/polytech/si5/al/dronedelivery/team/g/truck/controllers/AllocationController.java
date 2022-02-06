package com.polytech.si5.al.dronedelivery.team.g.truck.controllers;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.FleetAllocation;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.AllocationProvider;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneLauncher;
import com.polytech.si5.al.dronedelivery.team.g.truck.views.FleetAllocationView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AllocationController {
    Logger logger = LoggerFactory.getLogger(AllocationController.class);

    @Autowired
    DroneLauncher droneLauncher;

    @Autowired
    private AllocationProvider allocationProvider;

    @GetMapping(value="/truck/allocation")
    public List<FleetAllocationView> getAllocations () {
        // TODO: 16/01/2022 Return proper json formatted DTO
        List<FleetAllocation> allocations = allocationProvider.getAllocations();
        return allocations.stream().map(FleetAllocation::getView).collect(Collectors.toList());
    }

    @PostMapping("/start/drone/{droneId}")
    public void startDrone(@PathVariable Long droneId){
        logger.info("receive start demand");
        droneLauncher.startDrone(droneId);
    }

    @PostMapping("/start/fleet")
    public void startFleet(@RequestBody Long[] droneIds){
        logger.info("receive start demand");
        droneLauncher.startFleet(droneIds);
    }
}
