package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneFinder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Component
public class DroneRegistryBean implements DroneFinder {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Drone findDroneById(int droneId) throws IllegalArgumentException{
        Drone drone = entityManager.find(Drone.class, droneId);
        if(drone == null) throw new IllegalArgumentException("Drone " + droneId + " not found...");
        return drone;
    }
}
