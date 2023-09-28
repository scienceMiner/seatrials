package com.scienceminer.interviewcode.quickstart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.scienceminer.interviewcode.quickstart.CarColour.*;

public class CarFileReader {

    private static final String DELIMITER = ",";

    public static void main(String[] args) {
        String filePath = "/cardata.csv";
        List<Car> carList = parseCSV(filePath);

        // Do something with the parsed carList
        for (Car car : carList) {
            System.out.println(car.getColour() + ", " + car.getRegistration() + ", " + car.getMake().getName());
        }
    }

    public static List<Car> parseCSV(String filePath) {
        List<Car> carList = new ArrayList<>();

        try (InputStream inputStream = CarFileReader.class.getResourceAsStream(filePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(DELIMITER);

                if (fields.length == 3) {
                    String color = fields[0].trim();
                    String alphanumeric = fields[1].trim();
                    String manufacturer = fields[2].trim();

                    Car car = new Car(CarColour.valueOf(color.toUpperCase()), alphanumeric, Manufacturer.valueOf(manufacturer.toUpperCase()));
                    carList.add(car);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return carList;
    }
}

