package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneStateNotifier;
import com.polytech.si5.al.dronedelivery.team.g.truck.utils.TimeSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("droneTracker")
@EnableAsync
public class DroneTracker {
    private static final Logger logger = LoggerFactory.getLogger(DroneTracker.class);
    @Autowired
    DroneStateNotifier droneStateNotifier;

    @Autowired
    DroneFinder droneFinder;

    @Scheduled(fixedDelay = 10000)
    public void trackDrones() {
        List<Drone> drones = droneFinder.getDroneFlying();
        if (drones.size() > 0)
            logger.info("Drone in flight = " + drones);
        for (Drone drone : drones) {
            if (TimeSystem.getCurrentTimeSecond() - drone.getTimeStamp() > 10 && drone.getTimeStamp() != 0) {
                droneStateNotifier.droneDown(drone.getId());
            }
        }
    }

}
