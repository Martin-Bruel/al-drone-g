package com.polytech.si5.al.dronedelivery.team.g.truck.interfaces;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;

import java.util.List;

public interface DroneModifier {
    void assignDeliveryToDrone(Drone drone, List<Delivery> delivery);
}
