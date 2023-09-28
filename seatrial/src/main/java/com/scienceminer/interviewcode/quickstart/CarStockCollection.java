package com.scienceminer.interviewcode.quickstart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CarStockCollection extends ArrayList<Car> {

    private HashMap<Manufacturer, SummaryData> inventory;

    CarStockCollection() {
        super();
        inventory = new HashMap<>();
    }

    @Override
    public boolean add(Car car) {
        Manufacturer m = car.getMake();
        if (!inventory.containsKey(m)) {
            inventory.put(m, new SummaryData(car));
        }
        else {
            SummaryData s = inventory.get(m);
            s.increment(car.getColour());
        }
        return super.add(car);
    }

    public String statsString() {
        StringBuilder s = new StringBuilder();
        for ( Manufacturer m : inventory.keySet() ) {
            s.append(" \n\t " + m.toString() + " \n " );
            s.append(inventory.get(m).toString());
        }
        return s.toString();
    }

    private class SummaryData {
        private HashMap<CarColour, Integer> colourTotal;
        private int makeTotal;

        SummaryData() {
            colourTotal = new HashMap<>();
            makeTotal = 0;
        }

        SummaryData(Car c) {
            colourTotal = new HashMap<>();
            increment(c.getColour());
        }


        private void increment(CarColour c) {
            if (colourTotal.get(c) == null) {
                colourTotal.put(c,1);
            }
            else
                colourTotal.replace(c,colourTotal.get(c)+1);
        }

        public String toString() {
            StringBuilder s = new StringBuilder();
            for (CarColour c : colourTotal.keySet()) {
                s.append(" \n\t " + c.getColour() + " : " + colourTotal.get(c) ) ;
            }
            return s.toString();
        }
    }
}

