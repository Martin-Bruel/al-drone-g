package com.polytech.si5.al.dronedelivery.team.g.truck.utils;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;

public class PositionCalculator {

    public static double distance(Position p1, Position p2){
        double lat1 = p1.getLatitude();
        double lat2 = p2.getLatitude();
        double lon1 = p1.getLongitude();
        double lon2 = p2.getLongitude();

        double R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2-lat1);
        double dLon = deg2rad(lon2-lon1);
        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon/2) * Math.sin(dLon/2)
                ;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c; // Distance in km
        return d;
    }

    public static double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }
}
