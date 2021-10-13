package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneFinder;
import org.springframework.stereotype.Component;

@Component
public class DroneRegistryBean implements DroneFinder {
    @Override
    public Drone findDroneById(int droneId) {
        return null;
    }
}
