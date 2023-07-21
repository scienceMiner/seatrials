package com.scienceminer.interviewcode.quickstart;

public enum CarColour {

    RED("RED"),
    BLUE("BLUE"),
    BLACK("BLACK"),
    WHITE("WHITE"),
    GREEN("GREEN"),
    GREY ("GREY");

    private final String colour; // should have used this - final final final final final final
    CarColour(String colour) {
        this.colour = colour; // should have used this. - then no need for parameter to have a different name
    }

    public String getColour() {
        return colour;
    }
}
