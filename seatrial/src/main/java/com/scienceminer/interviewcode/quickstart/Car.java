package com.scienceminer.interviewcode.quickstart;

public class Car implements CarInterface {

    private final CarColour colour;
    private final String registration;
    private final Manufacturer make;

    public Car(CarColour colour, String registration, Manufacturer make) {
        this.colour = colour;
        this.registration = registration;
        this.make = make;
    }

    public CarColour getColour() {
        return colour;
    }

    public String getRegistration() {
        return registration;
    }

    public Manufacturer getMake() {
        return make;
    }

    @Override
    public String toString() {
        return "Car{make='" + getMake() + "', colour=" + getColour() +  ", registration=" + getRegistration() + "}";
    }


}
