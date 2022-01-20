package com.polytech.si5.al.dronedelivery.team.g.truck.utils;

public class TimeSystem {
    /**
     * Return time of system in second
     * @return time in second
     */
    public static long getCurrentTimeSecond(){
        return System.currentTimeMillis() / 1000;
    }
}
