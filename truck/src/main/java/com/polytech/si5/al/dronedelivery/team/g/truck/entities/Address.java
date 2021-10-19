package com.polytech.si5.al.dronedelivery.team.g.truck.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    public Address(String street, int number, int postalCode, String city, Position position) {
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.city = city;
        this.position = position;
    }

    public Address(Position position) {
        this.position = position;
    }

    public Address(){}

    private String street;
    private int number;
    private int postalCode;
    private String city;
    private Position position;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number=" + number +
                ", postalCode=" + postalCode +
                ", city='" + city + '\'' +
                ", position=" + position +
                '}';
    }
}
