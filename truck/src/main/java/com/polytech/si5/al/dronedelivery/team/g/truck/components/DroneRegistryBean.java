package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.DroneStatus;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@Component
public class DroneRegistryBean implements DroneFinder {

    @PersistenceContext
    private EntityManager entityManager;
    Logger logger = LoggerFactory.getLogger(DroneRegistryBean.class);


    @Override
    public Drone findDroneById(Long droneId) throws IllegalArgumentException{
        logger.info("find drone for id " + droneId);
        Drone drone = entityManager.find(Drone.class, droneId);
        if(drone == null) throw new IllegalArgumentException("Drone " + droneId + " not found...");
        return drone;
    }

    @Override
    public List<Drone> getAvailableDrones() {
        // TODO: 14/10/2021 Fetch 'READY' drones instead
        Drone d = new Drone();
        d.setStatus(DroneStatus.READY);
        return Arrays.asList(d);
    }
}
