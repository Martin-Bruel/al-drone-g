package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

public class Position {

    private int Latitude;
    private int longitude;

    public Position(int latitude, int longitude) {
        Latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Position{" +
                "Latitude=" + Latitude +
                ", longitude=" + longitude +
                '}';
    }
}
