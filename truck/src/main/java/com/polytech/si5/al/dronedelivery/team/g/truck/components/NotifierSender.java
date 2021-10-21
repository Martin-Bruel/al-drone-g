package com.polytech.si5.al.dronedelivery.team.g.truck.components;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Notification;
import com.polytech.si5.al.dronedelivery.team.g.truck.interfaces.NotificationFinder;
import com.polytech.si5.al.dronedelivery.team.g.truck.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class NotifierSender {

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    NotificationFinder notificationFinder;

    @Scheduled(fixedDelay = 5000)
    public void scheduleFixedDelayTask() {
        List<Notification> notifications = notificationFinder.getAllNotification();
        if(!notifications.isEmpty()){
            sendNotificationList(notifications);
        }
    }

    private void sendNotificationList(List<Notification> notificationList) {
        Notification[] notificationsTable = notificationList.toArray(new Notification[0]);
        System.out.println("array of notification : ");
        for(Notification not : notificationsTable){
            System.out.println(not);
        }

        try {
            warehouseService.sendNotifications(notificationList.toArray(new Notification[0]));
        } catch (IOException e){
            System.out.println("The warehouse can't be joined, the notifications will send later");
        }

    }
}
