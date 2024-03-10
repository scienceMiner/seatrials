package com.scienceminer.utils;

public class NumCharUtils {

    public static Integer checkIfDigit(char character){
        // System.out.println( " char is " + character );
        if (character >= '0' & character <= '9'){
            return Character.getNumericValue(character);
        } else {
            return null;
        }

    }

    public static Boolean isDigit(char character){
        // System.out.println( " char is " + character );
        if (character >= '0' & character <= '9'){
            return true;
        } else {
            return false;
        }

    }
}
