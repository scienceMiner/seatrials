package com.scienceminer.interviewcode.locktest;

import java.util.*;
import java.util.concurrent.locks.*;
 
/**
 * ReadWriteList.java
 * This class demonstrates how to use ReadWriteLock to add concurrency
 * features to a non-threadsafe collection
 * @author www.codejava.net
 */

public class SharedData<E> {
			
    private final List<E> list = new ArrayList<>();
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
 
    public SharedData(E... initialElements) {
        list.addAll(Arrays.asList(initialElements));
    }
 
    public void add(E element) {
        Lock writeLock = rwLock.writeLock();
        writeLock.lock();
 
        try {
            list.add(element);
        } finally {
            writeLock.unlock();
        }
    }
 
    public E get(int index) {
        Lock readLock = rwLock.readLock();
        readLock.lock();
 
        try {
            return list.get(index);
        } finally {
            readLock.unlock();
        }
    }
 
    public int size() {
        Lock readLock = rwLock.readLock();
        readLock.lock();
 
        try {
            return list.size();
        } finally {
            readLock.unlock();
        }
    }
 
}