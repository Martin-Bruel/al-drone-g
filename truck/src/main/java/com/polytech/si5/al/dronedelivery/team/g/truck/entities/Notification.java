package com.polytech.si5.al.dronedelivery.team.g.truck.entities;


import com.polytech.si5.al.dronedelivery.team.g.truck.constants.DeliveryStatusCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String description;

    public Notification(Long packageId, int deliverySate){
        if(deliverySate == DeliveryStatusCode.PACKAGE_DELIVERED){
            description = "The package " + packageId + " has been delivered.";
        } else {
            description = "packageId = " + packageId + ", deliverySate" + deliverySate;
        }
    }

    public Notification() {
        this.description = "An empty notification";
    }

    @Override
    public String toString() {
        return String.format(
                "Notification[id = %d, description= %s]", id, description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,description);
    }
}
