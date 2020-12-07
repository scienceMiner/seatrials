package com.scienceminer.interviewcode.threads;


public class CubbyHole {
		// lists if an int is 'available' to get
		// we must wait to get  the int when it is not available
		// we must wait to put  the int when it is available


    private int contents;
    private boolean available = false;

    public synchronized int get() {
        while (available == false) {
						System.out.println("Int NOT available");
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        available = false;
        notifyAll();
        return contents;
    }

    public synchronized void put(int value) {
        while (available == true) {
            try {
								System.out.println("Int PRESENT - cannot PUT");
                wait();
            } catch (InterruptedException e) { }
        }
        contents = value;
        available = true;
        notifyAll();
    }
}
