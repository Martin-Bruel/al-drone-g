package com.polytech.si5.al.dronedelivery.team.g.truck.dto;

import lombok.Getter;

@Getter
public class PositionDto {
    private String Latitude;
    private String longitude;

    @Override
    public String toString() {
        return "Position{" +
                "Latitude='" + Latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
