package com.scienceminer.interviewcode.quickstart;
class CarProcessor implements Runnable {
    private final Car car;

    public CarProcessor(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        // Process the car based on its manufacturer
        String manufacturer = car.getMake().getName();
        Manufacturer m1 = car.getMake();
        switch (m1) {
            case FORD:
                processFordCar(car);
                break;
            case TOYOTA:
                processToyotaCar(car);
                break;
            case HONDA:
                processHondaCar(car);
                break;
            case BMW:
                processBMWCar(car);
                break;
            case TESLA:
                processTeslaCar(car);
                break;
            default:
                // Handle other manufacturers if needed
                break;
        }
    }

    private void processFordCar(Car car) {
        // Process Ford car
        String threadName = Thread.currentThread().getName();
        System.out.println(" Thread name: " + threadName + " FORD is processing: " + car.getMake() + " with " + car.getColour());
    }

    private void processToyotaCar(Car car) {
        // Process Toyota car
        String threadName = Thread.currentThread().getName();
        System.out.println(" Thread name: " + threadName + " TOYOTA is processing: " + car.getMake() + " with " + car.getColour());
    }

    private void processHondaCar(Car car) {
        // Process Honda car
        String threadName = Thread.currentThread().getName();
        System.out.println(" Thread name: " + threadName + " HONDA is processing: " + car.getMake() + " with " + car.getColour());
    }

    private void processBMWCar(Car car) {
        // Process BMW car
        String threadName = Thread.currentThread().getName();
        System.out.println(" Thread name: " + threadName + " BMW is processing: " + car.getMake() + " with " + car.getColour());
    }

    private void processTeslaCar(Car car) {
        // Process Mercedes car
        String threadName = Thread.currentThread().getName();
        System.out.println(" Thread name: " + threadName + " TESLA is processing: " + car.getMake() + " with " + car.getColour());
    }

}