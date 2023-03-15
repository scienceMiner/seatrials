package com.scienceminer.interviewcode.locktest;

import java.util.*;

/**
 * Writer.java
 * This thread randomly read an element from a shared data structure
 * @author www.codejava.net
 */
public class Writer extends Thread {
	
    private SharedData<Integer> sharedList;
 
    public Writer(SharedData<Integer> sharedList) {
        this.sharedList = sharedList;
    }
 
    public void run() {
        Random random = new Random();
        int number = random.nextInt(100);
        sharedList.add(number);
 
        try {
            Thread.sleep(100);
            System.out.println(getName() + " -> put: " + number);
        } catch (InterruptedException ie ) { ie.printStackTrace(); }
    }
}