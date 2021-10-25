package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.constants.DeliveryStatusCode;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Drone;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.DroneStatus;
import com.polytech.si5.al.dronedelivery.team.g.truck.enumeration.DeliveryStatus;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class DeliveryTracker implements DeliveryStateNotifier, DroneStateNotifier {
    private static final Logger logger = LoggerFactory.getLogger(DeliveryTracker.class);
    @Autowired
    DroneWatcher droneWatcher;

    @Autowired
    NotificationRegistration notificationRegistration;

    @Autowired
    PackageFinder packageFinder;

    @Autowired
    DroneFinder droneFinder;

    @Autowired
    EntityManager entityManager;

    private boolean mustSendMessage=true;

    @Override
    @Transactional
    public void updateDeliverySate(long droneId, int status) {
        logger.info("Updating delivery of drone "+droneId + " (Status:"+status+")");
        switch (status){
            case DeliveryStatusCode.STARTING_DELIVERY:
                logger.info("Starting delivery ");
                droneWatcher.track(droneId);
                break;
            case DeliveryStatusCode.PENDING_DELIVERY:
                break;
            case DeliveryStatusCode.FINISHED_DELIVERY:
                logger.info("Finishing delivery ");
                Drone drone= droneFinder.findDroneById(droneId);
                drone.setStatus(DroneStatus.READY);
                droneWatcher.untrack(droneId);
                break;
            case DeliveryStatusCode.PACKAGE_DELIVERED:
                logger.info("Package delivered");
                List<Delivery> deliveries=packageFinder.getPackagesByDroneId(droneId);
                if(mustSendMessage){
                    sendNotification(droneId, status);
                }
                for(Delivery delivery : deliveries){
                    delivery.setDeliveryStatus(DeliveryStatus.DELIVERED);
                    delivery.setDeliveryDrone(null);
                    entityManager.persist(delivery);
                }
                break;
            default:
                break;
        }
    }

    void setMustSendMessage(boolean value){this.mustSendMessage=value;}

    void sendNotification(long droneId, int status) {
        List<Delivery> Deliveries = packageFinder.getPackagesByDroneId(droneId);
        for(Delivery delivery : Deliveries){
            notificationRegistration.createNotification(delivery.getId(), status);
        }
    }

    @Override
    @Transactional
    public void droneDown(long droneId) {
        List<Delivery> deliveries=packageFinder.getPackagesByDroneId(droneId);
        for(Delivery delivery : deliveries){
            delivery.setDeliveryStatus(DeliveryStatus.LOST);
            entityManager.merge(delivery);
        }
        Drone drone= droneFinder.findDroneById(droneId);
        drone.setStatus(DroneStatus.LOST);
    }
}
