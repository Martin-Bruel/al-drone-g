package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private int number;
    private int postalCode;
    private String city;
}
