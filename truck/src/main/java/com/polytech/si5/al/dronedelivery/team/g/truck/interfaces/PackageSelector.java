package com.polytech.si5.al.dronedelivery.team.g.truck.interfaces;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.DeliveryPoint;

import java.util.List;

public interface PackageSelector {
    public List<Delivery> getDeliverySelected();
    List<DeliveryPoint> getReachableDeliveryPoints();
}
