package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Allocation;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.AllocationProvider;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PositionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SchedulerBean implements AllocationProvider {

    @Autowired
    private PositionProvider positionProvider;
    @Autowired
    private PackageFinder packageFinder;
    @Autowired
    private DroneFinder droneFinder;


    @Override
    public List<Allocation> getAllocations() {
        Position pos = positionProvider.getTruckPosition();
        List<Delivery> packs = packageFinder.getDeliverablePackages();
        List<Drone> drones = droneFinder.getAvailableDrones();

        List<Allocation> allocations = new ArrayList<>();

        // Simple allocation
        for (int i = 0; i < Math.min(packs.size(), drones.size()); i++) {
            allocations.add(new Allocation(drones.get(i), packs.get(i)));
        }

        return allocations;
    }
}
