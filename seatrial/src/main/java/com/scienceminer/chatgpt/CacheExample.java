package com.scienceminer.chatgpt;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class CacheExample {
    private Map<String, WeakReference<ExpensiveObject>> cache;

    public CacheExample() {
        cache = new HashMap<>();
    }

    public ExpensiveObject getExpensiveObject(String key) {
        ExpensiveObject object = null;

        // Check if the object is available in the cache
        WeakReference<ExpensiveObject> weakRef = cache.get(key);
        if (weakRef != null) {
            object = weakRef.get();
        }

        // If the object is not available or has been garbage collected,
        // create a new object and store it in the cache
        if (object == null) {
            object = createExpensiveObject();
            cache.put(key, new WeakReference<>(object));
        }

        return object;
    }

    private ExpensiveObject createExpensiveObject() {
        // Simulating object creation
        return new ExpensiveObject();
    }

    // Example class representing an expensive object
    private static class ExpensiveObject {
        // Some expensive data or operations...
    }

    public static void main(String[] args) {
        CacheExample cacheExample = new CacheExample();

        // Retrieve the expensive object
        ExpensiveObject obj1 = cacheExample.getExpensiveObject("key1");
        System.out.println("Object 1: " + obj1);

        // Retrieve the same object again
        ExpensiveObject obj2 = cacheExample.getExpensiveObject("key1");
        System.out.println("Object 2: " + obj2);

        // Allow garbage collection and fetch the object again
        System.gc();
        ExpensiveObject obj3 = cacheExample.getExpensiveObject("key1");
        System.out.println("Object 3: " + obj3);
    }
}
