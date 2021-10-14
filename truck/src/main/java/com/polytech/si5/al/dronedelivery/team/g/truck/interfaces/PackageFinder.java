package com.polytech.si5.al.dronedelivery.team.g.truck.interfaces;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;

import java.util.List;

public interface PackageFinder {

    public List<Delivery> getDeliverablePackages();
    public Delivery getPackageByPackageId(Long packageId);
    public List<Delivery> getPackagesByDroneId(Long droneId);
}
