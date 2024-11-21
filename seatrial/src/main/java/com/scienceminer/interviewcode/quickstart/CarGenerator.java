package com.scienceminer.interviewcode.quickstart;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CarGenerator {
    private static final Random random = new Random();
    public static void main(String[] args) {

        CarStockCollection showroom = new CarStockCollection();
        //List<Car> showroom = new ArrayList<Car>(100000);

        for (int i = 0 ; i < 100000 ; i++ ) {
            CarColour randomCarColour = getRandomEnumValue(CarColour.class);
            Manufacturer randomCarManufacturer = getRandomEnumValue(Manufacturer.class);
            String registration = generateRandomString();

            Car c = new Car(randomCarColour, registration, randomCarManufacturer );

            //System.out.println("Inserting : " + c.toString() );
            showroom.add(c);
        }


        Map<String, ExecutorService> threadPools = new HashMap<>();
        threadPools.put(Manufacturer.TESLA.getName(), Executors.newSingleThreadExecutor(new NamedThreadFactory(Manufacturer.TESLA.getName())));
        threadPools.put(Manufacturer.FORD.getName(), Executors.newSingleThreadExecutor(new NamedThreadFactory(Manufacturer.FORD.getName())));
        threadPools.put(Manufacturer.BMW.getName(), Executors.newSingleThreadExecutor(new NamedThreadFactory(Manufacturer.BMW.getName())));
        threadPools.put(Manufacturer.HONDA.getName(), Executors.newSingleThreadExecutor(new NamedThreadFactory(Manufacturer.HONDA.getName())));
        threadPools.put(Manufacturer.TOYOTA.getName(), Executors.newSingleThreadExecutor(new NamedThreadFactory(Manufacturer.TOYOTA.getName())));

        for (Car car : showroom ) {
            String manufacturer = car.getMake().getName();
            ExecutorService manufacturerThreadPool = threadPools.get(manufacturer);
            manufacturerThreadPool.execute(new CarProcessor(car));
        }


        // Shutdown all thread pools when done
        for (ExecutorService pool : threadPools.values()) {
            System.out.println("shutdown" + pool.toString());
            pool.shutdown();

            while (!pool.isTerminated()) {
                // do nothing
                // wait for termination, better ways to do this
            }
        }

        System.out.println(showroom.statsString());

    }
    public static Car generateRandomCar() {

        CarColour color = getRandomEnumValue(CarColour.class);
        String alphanumeric = generateRandomString();
        Manufacturer manufacturer = getRandomEnumValue(Manufacturer.class);

        return new Car(color, alphanumeric, manufacturer);
    }

    private static String getRandomValue(String[] array) {
        int index = random.nextInt(array.length);
        return array[index];
    }
    public static CarColour getRandomCarColour() {
        CarColour[] colours = CarColour.values();
        int randomIndex = new Random().nextInt(colours.length);
        return colours[randomIndex];
    }

    public static <T extends Enum<?>> T getRandomEnumValue(Class<T> enumClass) {
        T[] enumValues = enumClass.getEnumConstants();
        int randomIndex = new Random().nextInt(enumValues.length);
        return enumValues[randomIndex];
    }

    public static String generateRandomString() {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String alphanumeric = letters + digits;

        StringBuilder sb = new StringBuilder();

        // First 2 characters are letters
        sb.append(getRandomChar(letters));
        sb.append(getRandomChar(letters));

        // Characters 3 and 4 are digits
        sb.append(getRandomChar(digits));
        sb.append(getRandomChar(digits));

        // Characters 5 to 7 are letters or digits
        for (int i = 0; i < 3; i++) {
            sb.append(getRandomChar(letters));
        }

        return sb.toString();
    }

    public static char getRandomChar(String charSet) {
        int randomIndex = new Random().nextInt(charSet.length());
        return charSet.charAt(randomIndex);
    }

}
    class NamedThreadFactory implements ThreadFactory {
        private final String threadPoolName;
        private final AtomicInteger counter = new AtomicInteger(1);

        public NamedThreadFactory(String threadPoolName) {
            this.threadPoolName = threadPoolName;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(threadPoolName + "-Thread-" + counter.getAndIncrement());
            return thread;
        }
    }
