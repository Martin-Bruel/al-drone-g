package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.DroneStatus;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.repositories.DroneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DroneRegistryBeanTest {

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    DroneFinder droneFinder;



//    @PersistenceContext
//    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        droneRepository.deleteAll();
    }

    @Test
    public void getAvailableDronesTest() {
        Drone a = new Drone(); a.setStatus(DroneStatus.FLYING_TO_DELIVERY);
        Drone b = new Drone(); b.setStatus(DroneStatus.FLYING_TO_TRUCK);
        Drone c = new Drone(); c.setStatus(DroneStatus.LOST);
        Drone d = new Drone(); d.setStatus(DroneStatus.READY);

        droneRepository.save(a);
        droneRepository.save(b);
        droneRepository.save(c);
        droneRepository.save(d);

        List<Drone> drones = droneFinder.getAvailableDrones();
        assertTrue(drones.contains(d));
        assertFalse(drones.contains(c));
    }

}