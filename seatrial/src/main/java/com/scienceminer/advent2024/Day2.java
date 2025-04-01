package com.scienceminer.advent2024;


import com.scienceminer.utils.FileUtils;
import com.scienceminer.utils.NumCharUtils;
import com.scienceminer.utils.enums.Num;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Day2 {

    private static String INPUTFILE = "/advent2024/inputDay2.txt";

    public static void main(String[] args) {

        ArrayList<String> arrList = FileUtils.readFileToArrayList(INPUTFILE);
        System.out.println(arrList.size());

        //int count = 0;
        int result = 0;
        ArrayList<Integer> intlist1 = new ArrayList<>();
        ArrayList<Integer> intlist2= new ArrayList<>();

        int safe = 0;
        for (String s : arrList) {

            String[] inputArr = s.split(" ");

            List<String> wordList = Arrays.asList(inputArr);

            List<Integer> resultList = new ArrayList<>(wordList.size());

            for (String s1 : wordList) {
                resultList.add(Integer.valueOf(s1));
            }


 //   if (resultList.get(0) == 23 && resultList.get(1) == 20) {
   //     System.out.println((resultList.toString()));
        if (isSafe(resultList)) {
            safe++;
        }

        if (isSafe(resultList) && !checkOrder(resultList))
            System.out.println("\t\t\t FAILURE FOR : " + resultList.toString() );

 //   }
            // System.out.println(" Nums are: " + int1 + " and " + int2);

            // For each resultList that is unsafe:
            // Run the check with each item dropped from the list

        }

        System.out.println(" number of safe: " + safe );


    // im getting 529 but the correct answer is 528 ????

    }

    private static boolean isSafe(List<Integer> inputList ) {
        int violation = 0;
        boolean ascend = false;
        boolean ascendUnset = true;
        boolean directionChangedOnce = false;
        boolean originalDirection = false;
        int directionChanged = 0;
        if (inputList.get(0) > inputList.get(1)) {
            ascend = false;
            ascendUnset = false;
            originalDirection = ascend;
        }
        else if (inputList.get(0) < inputList.get(1)) {
            ascend = true;
            ascendUnset = false;
            originalDirection = ascend;
        }
        else { // they are the same so unsafe
            ascendUnset = true;
        }


        int size = inputList.size();
        for (Integer i = 0; i < size-1; i++ ) {
            if (violation > 1)
                return false;
            if (directionChanged > 1)
                return true;
            Integer first = inputList.get(i);
            Integer second = inputList.get(i+1);
            if (first == second) {
                violation++;
                //           System.out.println("VIOLATION: " + violation + " F: " + first + " S: " + second + "DIFF: 0 " + " ASCEND: " + ascend);

            }
            else {
                int difference = first - second;
                if (Math.abs(difference) >= 1 && Math.abs(difference) <= 3) {
                    if (first > second) { // GOING DOWN
                //      if (directionChangedOnce && !ascend) {
                //              violation++;
                //       }
                        if ( ascend && !directionChangedOnce ) { // thinks we are going up but first > second
                            // violation is at most 1
                            ascend = false;
                            directionChangedOnce = true;
                            violation++;
                        }
                        else if ( ascend && directionChangedOnce ) {
                            violation++;
                        }

                        if (ascendUnset) {
                            ascend = false;
                            originalDirection = ascend;
                        }

                    }
                    if (first < second) { // GOING UP
              //          if (directionChangedOnce && ascend) {
                ///            violation++;
                   //     }
                        if (!ascend && !directionChangedOnce ) {
                            ascend = true;
                            directionChangedOnce = true;
                            violation++;
                        }
                        else if (!ascend && directionChangedOnce ) {
                            violation++;
                        }

                        if (ascendUnset)
                            ascend = true;
                            originalDirection = ascend;


                    }
                } else
                    violation++;

     //           System.out.println("VIOLATION: " + violation + " F: " + first + " S: " + second + "DIFF: " + difference + " ASCEND: " + ascend);

            }
        }


        if (violation > 1)
            return false;

    //    System.out.print("\t\t SAFE \n " );
        return true;
    }

    private static boolean checkOrder(List<Integer> inputList ) {
        int down = 0;
        int up = 0;

        for (Integer i = 0; i < inputList.size() - 1; i++) {
            Integer first = inputList.get(i);
            Integer second = inputList.get(i+1);
            if (first > second)
                down++;
            if (first < second)
                up++;

        }

        if (up > 1 && down > 1)
            return false;
        else
            return true;
    }

        private static Integer findNumberofTimein( Integer key1, ArrayList<Integer> intlist2 ) {
        int score = 0;

        for (int val : intlist2) {
            if (val == key1 )
                score++;
        }

        return score;
    }

    private static Integer findFirstNum(String s) {
       // System.out.println(" check first for : " + s );
        for( char c : s.toCharArray() ) {
            Integer val = NumCharUtils.checkIfDigit(c);
            //System.out.println(" check digit for : " + c );
            if (val != null)
                return val;
        }
        return null;
    }

    public static Integer findLastNum(String s1) {
        String s = new StringBuilder(s1).reverse().toString();
        for(char c : s.toCharArray()) {
            Integer val = NumCharUtils.checkIfDigit(c);
            if (val != null)
                return val;
        }
        return null;
    }

    public static String convert(String s1) {

        // THREEIGHT - check 3 before 8
        // NINEIGHT - check 9 before 8
        // TWONE - check 2 before 1
        // SEVENINE - check 7 before 9
        // FIVEIGHT - check 5 before 8
        // ONEIGHT - check 1 before 8
        //
        if (s1.contains(Num.TWO.getNum()))
            s1 = s1.replaceAll(Num.TWO.getNum(),"t2o");
        if (s1.contains(Num.ONE.getNum()))
            s1 = s1.replaceAll(Num.ONE.getNum(),"o1e");
        if (s1.contains(Num.THREE.getNum()))
            s1 = s1.replaceAll(Num.THREE.getNum(),"t3e");
        if (s1.contains(Num.FOUR.getNum()))
            s1 = s1.replaceAll(Num.FOUR.getNum(),"f4r");
        if (s1.contains(Num.SIX.getNum()))
            s1 = s1.replaceAll(Num.SIX.getNum(),"s6x");
        if (s1.contains(Num.FIVE.getNum()))
            s1 = s1.replaceAll(Num.FIVE.getNum(),"f5e");
        if (s1.contains(Num.SEVEN.getNum()))
            s1 = s1.replaceAll(Num.SEVEN.getNum(),"s7n");
        if (s1.contains(Num.NINE.getNum()))
            s1 = s1.replaceAll(Num.NINE.getNum(),"n9e");
        if (s1.contains(Num.EIGHT.getNum()))
            s1 = s1.replaceAll(Num.EIGHT.getNum(),"e8t");

        return s1;
    }

    public static String convertLast(String s1) {

        // EIGHTWO - check 2 before 8
        // THREEIGHT - check 8 before 3
        // NINEIGHT - check 8 before 9
        // TWONE - check 1 before 2
        // SEVENINE - check 9 before 7
        // FIVEIGHT - check 8 before 5
        // ONEIGHT - check 8 before one
        //
        if (s1.contains(Num.EIGHT.getNum()))
            s1 = s1.replaceAll(Num.EIGHT.getNum(),"8");
        if (s1.contains(Num.ONE.getNum()))
            s1 = s1.replaceAll(Num.ONE.getNum(),"1");
        if (s1.contains(Num.TWO.getNum()))
            s1 = s1.replaceAll(Num.TWO.getNum(),"2");
        if (s1.contains(Num.FOUR.getNum()))
            s1 = s1.replaceAll(Num.FOUR.getNum(),"4");
        if (s1.contains(Num.SIX.getNum()))
            s1 = s1.replaceAll(Num.SIX.getNum(),"6");
        if (s1.contains(Num.FIVE.getNum()))
            s1 = s1.replaceAll(Num.FIVE.getNum(),"5");
        if (s1.contains(Num.NINE.getNum()))
            s1 = s1.replaceAll(Num.NINE.getNum(),"9");
        if (s1.contains(Num.SEVEN.getNum()))
            s1 = s1.replaceAll(Num.SEVEN.getNum(),"7");
        if (s1.contains(Num.THREE.getNum()))
            s1 = s1.replaceAll(Num.THREE.getNum(),"3");

        return s1;
    }




}