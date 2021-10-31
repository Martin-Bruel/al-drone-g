package com.polytech.si5.al.dronedelivery.team.g.truck.utils;

import com.polytech.si5.al.dronedelivery.team.g.truck.entities.Position;

import java.util.ArrayList;
import java.util.List;

public class PositionCalculator {

    public static double distance(Position p1, Position p2){
        double xP1 = p1.getLongitude();
        double yP1 = p1.getLatitude();
        double xP2 = p2.getLongitude();
        double yP2 = p2.getLatitude();

        return Math.pow(Math.pow(Math.abs(xP1 - xP2), 2) + Math.pow(Math.abs(yP1 - yP2), 2), 0.5);
    }

    public static List<Position> positionsBetweenTwoPosition(Position p1, Position p2){

        List<Position> positions = new ArrayList<>();
        double nbPosition = PositionCalculator.distance(p1, p2);
        for (int j = 0; j < nbPosition; j++) {
            double lat = p1.getLatitude() + p2.getLatitude() / nbPosition;
            double longs = p1.getLongitude() + p2.getLongitude() / nbPosition;
            positions.add(new Position(lat, longs));
        }
        return positions;
    }
}
