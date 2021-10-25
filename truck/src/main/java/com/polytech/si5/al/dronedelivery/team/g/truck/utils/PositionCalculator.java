package com.polytech.si5.al.dronedelivery.team.g.truck.utils;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;

public class PositionCalculator {

    public static double distance(Position p1, Position p2){
        int xP1 = p1.getLongitude();
        int yP1 = p1.getLatitude();
        int xP2 = p2.getLongitude();
        int yP2 = p2.getLatitude();

        int ac = Math.abs(yP2 - yP1);
        int cb = Math.abs(xP2 - xP1);

        return Math.hypot(ac, cb);
    }
}
