package com.scienceminer.interviewcode.locktest;

/**
 * ReadWriteLockTest.java
 * Test program for understanding ReadWriteLock
 * @author www.codejava.net
 */
public class ReadWriteLockTest {
    static final int READER_SIZE = 10;
    static final int WRITER_SIZE = 2;
 
    public static void main(String[] args) {
        Integer[] initialElements = {33, 28, 86, 99};
 
        final SharedData<Integer> sharedList = new SharedData<>(initialElements);
 
        for (int i = 0; i < WRITER_SIZE; i++) {
            new Writer(sharedList).start();
        }
 
        for (int i = 0; i < READER_SIZE; i++) {
            new Reader(sharedList).start();
        }
 
    }
}