package com.polytech.si5.al.dronedelivery.team.g.truck.dto;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;
import lombok.Getter;

@Getter
public class PositionDroneDto {

    private long droneId;
    private Position position;
    private long timestamp;

    @Override
    public String toString() {
        return "PositionDroneDto{" +
                "droneId=" + droneId +
                ", position=" + position +
                ", timestamp=" + timestamp +
                '}';
    }
}
