package com.scienceminer.advent2023;


import main.java.com.scienceminer.utils.FileUtils;

import java.util.ArrayList;


public class Day2 {
    private static String INPUTFILE = "/advent2023/inputDay2.txt";
    public static void main(String[] args) {

        ArrayList<String> arrList = FileUtils.readFileToArrayList(INPUTFILE);

        int count = 1;
        int result = 0;
        int powerSum = 0;
        Trio trio = new Trio(12, 13, 14);

        for (String s : arrList) {

            String vals = s.split(":")[1];
            String key = s.split(":")[0];

            String[] selection = vals.split(";");

            boolean isValid = checkIfValid(selection, trio);
            int power = findMaxTrio(selection);
            if (isValid)
                result = count + result;

            count++;
            powerSum = powerSum + power;
        }

        System.out.println(" RESULT: " + result + " POWER: " + powerSum);

    }

    public static boolean checkIfValid(String[] selections, Trio game) {

        System.out.println(game.toString());
        for (String s : selections) {

            System.out.println(" Comparing against: " + new Trio(s).toString());
            if (!checkIfValid(game, s))
                return false;
        }

        System.out.println(" VALID ");
        return true;
    }

    public static int findMaxTrio(String[] selections ) {
        Trio maxT = new Trio(0,0,0);

        for (String s : selections) {
            Trio input = new Trio(s);
            if (input.getBlue() > maxT.getBlue())
                maxT.setBlue(input.getBlue());
            if (input.getRed() > maxT.getRed())
                maxT.setRed(input.getRed());
            if (input.getGreen() > maxT.getGreen())
                maxT.setGreen(input.getGreen());

        }

        int power = maxT.getBlue() * maxT.getRed() * maxT.getGreen();
        return power;
    }

    public static boolean checkIfValid(Trio game, String s) {
        Trio t1 = new Trio(s);
        if (game.isValid(t1))
            return true;

        return false;
    }

    public static Integer checkIfDigit(char character) {
        // System.out.println( " char is " + character );
        if (character >= '0' & character <= '9') {
            return Character.getNumericValue(character);
        } else {
            return null;
        }
    }

    public static class Trio {
        int red;
        int green;
        int blue;

        Trio() {
            red = 0;
            green = 0;
            blue = 0;
        }

        public Trio(int r, int g, int b) {
            red = r;
            green = g;
            blue = b;
        }

        public Trio(String s1) {
            String[] vals = s1.split(",");
            for (String s : vals) {
                if (s.toUpperCase().contains("GREEN"))
                    setGreen(extractDigit(s));
                if (s.toUpperCase().contains("RED"))
                    setRed(extractDigit(s));
                if (s.toUpperCase().contains("BLUE"))
                    setBlue(extractDigit(s));
            }
        }

        private int extractDigit(String str) {
            String num = str.replaceAll("[^\\d]", "");
            return Integer.parseInt(num);
        }
        public void setGreen(int g) {
            green = g;
        }
        public void setBlue(int b) {
            blue = b;
        }
        public void setRed(int r) {
            red = r;
        }
        public int getGreen() {
            return green;
        }
        public int getBlue() {
            return blue;
        }
        public int getRed() {
            return red;
        }
        public boolean isValid(Trio inputTrio) {
            if (getGreen() < inputTrio.getGreen())
                return false;
            if (getRed() < inputTrio.getRed())
                return false;
            if (getBlue() < inputTrio.getBlue())
                return false;

            return true;
        }

        public String toString() {
            return new String(" RED: " + getRed() + " GREEN: " + getGreen() + " BLUE: " + getBlue());
        }

    }

}