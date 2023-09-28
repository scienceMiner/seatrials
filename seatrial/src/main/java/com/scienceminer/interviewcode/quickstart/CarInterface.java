package com.scienceminer.interviewcode.quickstart;

import java.lang.management.ManagementFactory;

public interface CarInterface {
    //public enum CarColour{ WHITE, GREY , NAVY , BLACK } ; // foolish should have made public

    public Manufacturer getMake();
    public String getRegistration();

    public CarColour getColour();

}
