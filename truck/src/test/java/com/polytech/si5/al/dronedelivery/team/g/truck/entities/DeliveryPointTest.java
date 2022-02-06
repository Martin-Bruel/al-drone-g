package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeliveryPointTest {

    @Test
    public void testDeliveryPointPosition() {
        Delivery d1 = new Delivery(new Position(64,9));
        Delivery d2 = new Delivery(new Position(44,7));
        Delivery d3 = new Delivery(new Position(12,5));

        DeliveryPoint dp = new DeliveryPoint(Arrays.asList(d1,d2,d3));
        assertEquals(new Position(40.0, 7.0), dp.getPosition());
    }

}