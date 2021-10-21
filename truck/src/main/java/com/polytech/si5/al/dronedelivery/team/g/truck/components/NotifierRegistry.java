package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Notification;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.NotificationFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.NotificationModifier;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.NotificationRegistration;
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
public class NotifierRegistry implements NotificationRegistration, NotificationFinder, NotificationModifier {

    @Autowired
    WarehouseService warehouseService;

    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(NotifierRegistry.class);

    @Override
    public void createNotification(long packageId, int deliverySate) {
        logger.info("Notify packageId : " + packageId+", deliverySate : "+deliverySate);
        Notification notification = new Notification(packageId, deliverySate);
        registerNotification(notification);
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

    @Override
    @Transactional
    public void deleteNotificationsByIds(List<Long> notificationsIds) {
        entityManager.createQuery("DELETE FROM Notification n WHERE n.id IN (:ids)")
                .setParameter("ids", notificationsIds)
                .executeUpdate();
    }
}