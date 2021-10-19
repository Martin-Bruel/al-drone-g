package com.polytech.si5.al.dronedelivery.team.g.truck.interfaces;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Delivery;
import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRegistration {
    void registerNotification(Notification n);
}