package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Position {

    private double latitude;
    private double longitude;

    public Position(){}

    public Position(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude,longitude);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Position)) return false;
        Position position = (Position) other;
        return this.latitude == position.latitude && this.longitude == position.longitude;
    }

    @Override
    public String toString() {
        return "Position(latitude=" + latitude + ", longitude=" + longitude + ')';
    }
}
