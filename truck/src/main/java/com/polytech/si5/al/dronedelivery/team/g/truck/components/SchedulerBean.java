package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.*;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.AllocationProvider;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneModifier;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageSelector;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class SchedulerBean implements AllocationProvider {

    Logger logger = LoggerFactory.getLogger(SchedulerBean.class);


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
    public List<FleetAllocation> getAllocations() {
        logger.info("Get allocations");
        List<DeliveryPoint> deliveryPoints = packageSelector.getReachableDeliveryPoints();
        List<Drone> drones = droneFinder.getAvailableDrones();
        List<FleetAllocation> fleetAllocations = new ArrayList<>();

        for ( DeliveryPoint dp : deliveryPoints) {
            List<Allocation> allocations = allocate(dp.getDeliveries(), drones);
            fleetAllocations.add(new FleetAllocation(allocations));
        }

        return fleetAllocations;
    }

    private List<Allocation> allocate(List<Delivery> deliveries, List<Drone> drones) {
        List<Allocation> allocations = new ArrayList<>();

        drones.sort(Comparator.comparingInt(Drone::getCapacity).reversed());

        for (Drone drone : drones) {
            int amount = Math.min(deliveries.size(), drone.getCapacity());
            List<Delivery> selection = new ArrayList<>(deliveries.subList(0,amount));
            deliveries.removeAll(selection);
            if(!selection.isEmpty()) allocations.add(new Allocation(drone,selection));
        }

        for (Allocation a : allocations) {
            Drone drone = entityManager.merge(a.getDrone());
            Hibernate.initialize(drone.getDeliveries());

            List<Delivery> deliveryList = new ArrayList<>();
            for (Delivery d : a.getDeliveries()) {
                Delivery merged = entityManager.merge(d);
                Hibernate.initialize(merged.getDeliveryDrone());
                deliveryList.add(merged);
            }
            droneModifier.assignDeliveryToDrone(drone,deliveryList);
            drones.remove(drone);
        }
        return allocations;
    }
}
