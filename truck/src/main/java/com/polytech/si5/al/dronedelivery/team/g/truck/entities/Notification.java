package com.polytech.si5.al.dronedelivery.team.g.truck.entities;


import com.polytech.si5.al.dronedelivery.team.g.truck.constants.DeliveryStatusCode;

public class Notification {

    private String description;

    public Notification(Long packageId, int deliverySate){
        if(deliverySate == DeliveryStatusCode.PACKAGE_DELIVER){
            description = "The package " + packageId + " has been delivered.";
        } else {
            description = "packageId = " + packageId + ", deliverySate" + deliverySate;
        }
    }

    public String getDescription() { return description; }
}
