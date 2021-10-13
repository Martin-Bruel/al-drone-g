package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageFinder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PackageRegistryBean implements PackageFinder {
    @Override
    public List<Delivery> getDeliverablePackages() {
        return null;
    }

    @Override
    public Delivery getPackageByPackageId(int packageId) {
        return null;
    }

    @Override
    public List<Delivery> getPackagesByDroneId(int droneId) {
        return null;
    }
}
