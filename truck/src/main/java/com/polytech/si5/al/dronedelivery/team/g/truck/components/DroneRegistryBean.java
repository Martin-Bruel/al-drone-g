package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.DroneStatus;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneModifier;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class DroneRegistryBean implements DroneFinder, DroneModifier, DroneRegistration {

    @PersistenceContext
    private EntityManager entityManager;
    Logger logger = LoggerFactory.getLogger(DroneRegistryBean.class);


    @Override
    public Drone findDroneById(Long droneId) throws IllegalArgumentException{
        logger.info("find drone for id " + droneId);
        Drone drone = entityManager.find(Drone.class, droneId);
        if(drone == null) throw new IllegalArgumentException("Drone " + droneId + " not found...");
        return drone;
    }

    @Override
    @Transactional
    public List<Drone> getAvailableDrones() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Drone> cq = builder.createQuery(Drone.class);
        Root<Drone> drone = cq.from(Drone.class);
        cq.select(drone);

        cq.where(builder.equal(drone.get("status"), DroneStatus.READY));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    @Transactional
    public void assignDeliveryToDrone(Drone drone, Delivery delivery) {
        delivery = entityManager.merge(delivery);
        drone = entityManager.merge(drone);
        drone.getDeliveries().add(delivery);
        entityManager.persist(drone);
        // TODO: 15/10/2021 Use cascading instead
        delivery.setDeliveryDrone(drone);
        entityManager.persist(delivery);
    }


    @Override
    @Transactional
    public void registerDrone(Drone d) {
        entityManager.persist(d);
    }
}
