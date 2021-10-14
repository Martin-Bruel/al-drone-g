package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageFinder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PackageRegistryBean implements PackageFinder {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Delivery> getDeliverablePackages() {
        return (List<Delivery>) entityManager.createQuery(
                "SELECT e FROM Delivery e", Delivery.class).getResultList();
    }

    @Override
    public Delivery getPackageByPackageId(int packageId) {
        Delivery delivery = entityManager.find(Delivery.class, packageId);
        if(delivery == null) throw new IllegalArgumentException("Drone " + packageId + " not found...");
        return delivery;
    }

    @Override
    public List<Delivery> getPackagesByDroneId(int droneId) {
        return (List<Delivery>) entityManager.createQuery(
                "SELECT e FROM Delivery e WHERE e.deliveryDrone = " + droneId, Delivery.class).getResultList();
    }
}
