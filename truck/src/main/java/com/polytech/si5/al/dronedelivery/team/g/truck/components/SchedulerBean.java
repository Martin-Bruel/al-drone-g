package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Allocation;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class SchedulerBean implements AllocationProvider {

    @Autowired
    private PackageSelector packageSelector;
    @Autowired
    private DroneModifier droneModifier;
    @Autowired
    private DroneFinder droneFinder;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Allocation> getAllocations() {
        List<Delivery> packs = packageSelector.getDeliverySelected();
        List<Drone> drones = droneFinder.getAvailableDrones();

        List<Allocation> allocations = new ArrayList<>();

        // Simple allocation
        for (int i = 0; i < Math.min(packs.size(), drones.size()); i++) {
            Drone drone = entityManager.merge(drones.get(i));
            Delivery pack = entityManager.merge(packs.get(i));
            droneModifier.assignDeliveryToDrone(drone,pack);

            Hibernate.initialize(drone.getDeliveries());
            Hibernate.initialize(pack.getDeliveryDrone());
            allocations.add(new Allocation(drone, pack));
        }

        return allocations;
    }
}
