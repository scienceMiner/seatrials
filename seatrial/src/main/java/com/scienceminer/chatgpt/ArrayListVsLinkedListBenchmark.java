package com.scienceminer.chatgpt;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListVsLinkedListBenchmark {
    public static void main(String[] args) {
        int iterations = 100000;

        // Benchmarking ArrayList
        long arrayListStartTime = System.nanoTime();
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            arrayList.add(i);
        }
        long arrayListEndTime = System.nanoTime();
        long arrayListDuration = arrayListEndTime - arrayListStartTime;
        System.out.println("ArrayList time: " + arrayListDuration + " nanoseconds");

        // Benchmarking LinkedList
        long linkedListStartTime = System.nanoTime();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < iterations; i++) {
            linkedList.add(i);
        }
        long linkedListEndTime = System.nanoTime();
        long linkedListDuration = linkedListEndTime - linkedListStartTime;
        System.out.println("LinkedList time: " + linkedListDuration + " nanoseconds");

        // Accessing elements at the middle of the list
        int middleIndex = iterations / 2;

        long arrayListAccessStartTime = System.nanoTime();
        int arrayListElement = arrayList.get(middleIndex);
        long arrayListAccessEndTime = System.nanoTime();
        long arrayListAccessDuration = arrayListAccessEndTime - arrayListAccessStartTime;
        System.out.println("ArrayList access time: " + arrayListAccessDuration + " nanoseconds");

        long linkedListAccessStartTime = System.nanoTime();
        int linkedListElement = linkedList.get(middleIndex);
        long linkedListAccessEndTime = System.nanoTime();
        long linkedListAccessDuration = linkedListAccessEndTime - linkedListAccessStartTime;
        System.out.println("LinkedList access time: " + linkedListAccessDuration + " nanoseconds");
    }
}
