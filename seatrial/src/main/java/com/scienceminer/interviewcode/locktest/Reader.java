package com.scienceminer.interviewcode.locktest;

import java.util.*;

/**
 * Reader.java
 * This thread randomly adds an element to a shared data structure
 * @author www.codejava.net
 */
public class Reader extends Thread {
	
    private SharedData<Integer> sharedList;
 
    public Reader(SharedData<Integer> sharedList) {
        this.sharedList = sharedList;
    }
 
    public void run() {
        Random random = new Random();
        int index = random.nextInt(sharedList.size());
        Integer number = sharedList.get(index);
 
        System.out.println(getName() + " -> get: " + number);
 
        try {
            Thread.sleep(100);
        } catch (InterruptedException ie ) { ie.printStackTrace(); }
 
    }
    
}