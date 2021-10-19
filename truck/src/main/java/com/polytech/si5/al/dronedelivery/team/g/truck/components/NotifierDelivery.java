package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Notification;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.NotificationRegistration;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.Notifier;
import com.polytech.si5.al.dronedelivery.team.g.truck.services.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class NotifierDelivery implements Notifier, NotificationRegistration {

    @Autowired
    WarehouseService warehouseService;

    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(NotifierDelivery.class);

    @Override
    public void notifyDelivered(long packageId, int deliverySate) {
        logger.info("Notify packageId : " + packageId+", deliverySate : "+deliverySate);
        Notification notification = new Notification(packageId, deliverySate);
        testPersistNotification(notification);
        warehouseService.sendNotification(notification);
    }

    private void testPersistNotification(Notification notification){
        System.out.println("Notification to persist = "+notification);
        registerNotification(notification);
        System.out.println("Persisted");
        System.out.println("Notifications in database :");
        List<Notification> Notifications = getAllNotification();
        for(Notification n : Notifications){
            System.out.println(n);
        }
        System.out.println("End of the test for the notifications");
    }

    @Override
    @Transactional
    public void registerNotification(Notification n) {
        entityManager.persist(n);
    }

    @Transactional
    public List<Notification> getAllNotification(){
        return (List<Notification>) entityManager.createQuery(
                "SELECT e FROM Notification e", Notification.class).getResultList();
    }
}
