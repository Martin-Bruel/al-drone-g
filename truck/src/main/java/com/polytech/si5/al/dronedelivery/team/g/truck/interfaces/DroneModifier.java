package com.polytech.si5.al.dronedelivery.team.g.truck.interfaces;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.DroneStatus;

public interface DroneModifier {
    void assignDeliveryToDrone(Drone drone, Delivery delivery);
    void setDroneStatus(Drone drone, DroneStatus droneStatus);
}
