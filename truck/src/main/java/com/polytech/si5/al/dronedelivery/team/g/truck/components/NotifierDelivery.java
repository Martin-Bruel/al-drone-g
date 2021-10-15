package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Notification;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.Notifier;
import com.polytech.si5.al.dronedelivery.team.g.truck.services.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotifierDelivery implements Notifier {

    @Autowired
    WarehouseService warehouseService;

    private static final Logger logger = LoggerFactory.getLogger(NotifierDelivery.class);

    @Override
    public void notifyDelivered(long packageId, int deliverySate) {
        logger.info("Notify packageId : " + packageId+", deliverySate : "+deliverySate);
        Notification notification = new Notification(packageId, deliverySate);
        warehouseService.sendNotification(notification);
    }
}
