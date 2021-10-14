package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PackageRegistryBean implements PackageFinder {

    @PersistenceContext
    private EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(PackageRegistryBean.class);


    @Override
    public List<Delivery> getDeliverablePackages() {
        return (List<Delivery>) entityManager.createQuery(
                "SELECT e FROM Delivery e", Delivery.class).getResultList();
    }

    @Override
    public Delivery getPackageByPackageId(Long packageId) {
       logger.info("find delivery for id " + packageId);
        Delivery delivery = entityManager.find(Delivery.class, packageId);
        if(delivery == null) throw new IllegalArgumentException("Drone " + packageId + " not found...");
        return delivery;
    }

    @Override
    public List<Delivery> getPackagesByDroneId(Long droneId) {
        return (List<Delivery>) entityManager.createQuery(
                "SELECT e FROM Delivery e WHERE e.deliveryDrone = " + droneId, Delivery.class).getResultList();
    }
}
