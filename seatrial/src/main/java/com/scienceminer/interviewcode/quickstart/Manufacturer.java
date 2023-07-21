package com.scienceminer.interviewcode.quickstart;

public enum Manufacturer {

    FORD("Ford"),
    TOYOTA("Toyota"),
    HONDA("Honda"),
    BMW("BMW"),
    TESLA ("TESLA");

    private final String name; // should have used this - final final final final final final
    Manufacturer(String inputName) {
        this.name = inputName; // should have used this. - then no need for parameter to have a different name
    }

    public String getName() {
        return name;
    }
}
