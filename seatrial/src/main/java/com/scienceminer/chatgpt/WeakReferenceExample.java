package com.scienceminer.chatgpt;

import java.lang.ref.WeakReference;

public class WeakReferenceExample {
    public static void main(String[] args) {
        // Create an object
        Object object = new Object();

        // Create a weak reference to the object
        WeakReference<Object> weakRef = new WeakReference<>(object);

        // Make the object eligible for garbage collection
        object = null;

        // Attempt to retrieve the object from the weak reference
        Object retrievedObject = weakRef.get();

        // The retrieved object might still be available
        System.out.println("Retrieved Object: " + retrievedObject);

        // Explicitly request garbage collection
        System.gc();

        // Check if the object was garbage collected
        retrievedObject = weakRef.get();
        System.out.println("Retrieved Object after GC: " + retrievedObject);
    }
}
