package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class Position {

    private int Latitude;
    private int longitude;

    public Position(){}

    public Position(int latitude, int longitude) {
        Latitude = latitude;
        this.longitude = longitude;
    }

    public int getLatitude() {
        return Latitude;
    }

    public void setLatitude(int latitude) {
        Latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "{" +
                "Latitude=" + Latitude +
                ", longitude=" + longitude +
                '}';
    }
}
