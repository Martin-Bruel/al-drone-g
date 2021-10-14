package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.constants.DeliveryStatusCode;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DeliveryStateNotifier;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneStateNotifier;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.DroneWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryTracker implements DeliveryStateNotifier, DroneStateNotifier {
    private static final Logger logger = LoggerFactory.getLogger(DeliveryTracker.class);
    @Autowired
    DroneWatcher droneWatcher;

    @Override
    public void updateDeliverySate(int droneId, int status) {
        logger.info(String.valueOf(status));
        switch (status){
            case DeliveryStatusCode.STARTING_DELIVERY:
                droneWatcher.track(droneId);
                break;
            case DeliveryStatusCode.PENDING_DELIVERY:
                break;
            default:
                break;
        }
    }

    @Override
    public void droneDown(int droneId) {

    }
}
