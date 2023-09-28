package com.scienceminer.chatgpt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DomainOrderings {

    public static List<int[]> getAllOrderings(int[] inputArray) {
        List<int[]> outputList = new ArrayList<>();

        // Base case: if the input array has only one element, return it
        if (inputArray.length == 1) {
            outputList.add(inputArray);
            return outputList;
        }

        // Recursive case: for each element in the input array, get all orderings of the remaining elements
        for (int i = 0; i < inputArray.length; i++) {
            int[] subArray = new int[inputArray.length - 1];
            for (int j = 0; j < subArray.length; j++) {
                if (j < i) {
                    subArray[j] = inputArray[j];
                } else {
                    subArray[j] = inputArray[j+1];
                }
            }

            List<int[]> subList = getAllOrderings(subArray);

            // For each sub-ordering, add the current element to every position in the array
            for (int[] subOrdering : subList) {
                for (int k = 0; k <= subOrdering.length; k++) {
                    int[] outputArray = new int[inputArray.length];
                    for (int m = 0; m < outputArray.length; m++) {
                        if (m < k) {
                            outputArray[m] = inputArray[i];
                        } else if (m == k) {
                            outputArray[m] = inputArray[i];
                        } else {
                            outputArray[m] = subOrdering[m-1];
                        }
                    }
                    // Add the output array to the list if it is not a duplicate
                    if (!containsArray(outputList, outputArray)) {
                        outputList.add(outputArray);
                    }
                }
            }
        }

        return outputList;
    }

    // Helper function to check if a list of arrays contains a specific array
    private static boolean containsArray(List<int[]> list, int[] array) {
        for (int[] a : list) {
            if (Arrays.equals(a, array)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] inputArray = {-1, 1,1,-1};
        List<int[]> outputList = getAllOrderings(inputArray);
        for (int[] outputArray : outputList) {
            for (int i = 0; i < outputArray.length; i++) {
                System.out.print(outputArray[i] + " ");
            }
            System.out.println();
        }
    }
}
