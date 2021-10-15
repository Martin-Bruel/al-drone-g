package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.constants.DeliveryStatusCode;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DeliveryStateNotifier;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneStateNotifier;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneWatcher;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.PackageFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveryTracker implements DeliveryStateNotifier, DroneStateNotifier {
    private static final Logger logger = LoggerFactory.getLogger(DeliveryTracker.class);
    @Autowired
    DroneWatcher droneWatcher;

    @Autowired
    NotifierDelivery notifierDelivery;

    @Autowired
    PackageFinder packageFinder;

    @Override
    public void updateDeliverySate(long droneId, int status) {
        logger.info("Updating delivery of drone "+droneId);
        switch (status){
            case DeliveryStatusCode.STARTING_DELIVERY:
                logger.info("Starting delivery ");
                droneWatcher.track(droneId);
                break;
            case DeliveryStatusCode.PENDING_DELIVERY:
                break;
            case DeliveryStatusCode.FINISHED_DELIVERY:
                logger.info("Finishing delivery ");
                droneWatcher.untrack(droneId);
                break;
            case DeliveryStatusCode.PACKAGE_DELIVER:
                logger.info("Package deliver");
                sendNotification(droneId, status);
                break;
            default:
                break;
        }
    }

    void sendNotification(long droneId, int status) {
        List<Delivery> Deliveries = packageFinder.getPackagesByDroneId(droneId);
        for(Delivery delivery : Deliveries){
            notifierDelivery.notifyDelivered(delivery.getId(), status);
        }
    }

    @Override
    public void droneDown(long droneId) {

    }
}
