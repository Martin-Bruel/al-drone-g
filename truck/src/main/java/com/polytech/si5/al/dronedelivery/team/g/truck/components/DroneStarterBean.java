package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.*;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.*;
import com.polytech.si5.al.dronedelivery.team.g.truck.services.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DroneStarterBean implements DroneLauncher {

    @Autowired
    PositionProvider positionProvider;

    @Autowired
    PackageFinder packageFinder;

    @Autowired
    PathFinder pathFinder;

    @Autowired
    DroneFinder droneFinder;

    @Autowired
    DroneService droneService;

    Logger logger = LoggerFactory.getLogger(DroneStarterBean.class);


    @Override
    public void startDrone(Long droneId) {

        Position truckPos = positionProvider.getTruckPosition();
        List<Delivery> deliveries = droneFinder.findDroneById(droneId).getDeliveries();
        logger.info("Starting drone " + droneId + " with package " + deliveries.stream().map((d)->d.getId().toString()).collect(Collectors.toList()));

        FlightPlan flightPlan = pathFinder.getPath(truckPos, deliveries);
        Drone drone = droneFinder.findDroneById(droneId);

        List<Drone> drones = new ArrayList<>();
        drones.add(drone);
        Fleet fleet = new Fleet(drones, droneId, flightPlan);
        droneService.launchDrone(fleet);
    }

    @Override
    public void startFleet(Long[] droneIds) {

        Position truckPos = positionProvider.getTruckPosition();
        List<Delivery> deliveries = droneFinder.findDroneById(droneIds[0]).getDeliveries();
        logger.info("Starting drones " + droneIds + " with package " + deliveries.stream().map((d)->d.getId().toString()).collect(Collectors.toList()));

        FlightPlan flightPlan = pathFinder.getPath(truckPos, deliveries);

        List<Drone> drones = new ArrayList<>();
        for(Long droneId : droneIds){
            drones.add(droneFinder.findDroneById(droneId));
        }

        Fleet fleet = new Fleet(drones,droneIds[0],flightPlan);
        droneService.launchDrone(fleet);
    }


}
