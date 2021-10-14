package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.FlightPlan;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.*;
import com.polytech.si5.al.dronedelivery.team.g.truck.services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Override
    public void start(int droneId, int packageId) {
        System.out.println("Drone Starter - starting drone " + droneId + " with package " + packageId);
        Position truckPos = positionProvider.getTruckPosition();
        Position packagePos = packageFinder.getPackageByPackageId(packageId).getAddress().getPosition();
        FlightPlan flightPlan = pathFinder.getPath(truckPos, packagePos);
        Drone drone = droneFinder.findDroneById(droneId);
        droneService.launchDrone(flightPlan, drone);
    }
}
