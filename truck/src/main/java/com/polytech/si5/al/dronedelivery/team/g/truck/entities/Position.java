package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Position {

    private int latitude;
    private int longitude;

    public Position(){}

    public Position(int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Position{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
