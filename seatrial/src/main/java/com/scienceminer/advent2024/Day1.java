package com.scienceminer.advent2024;


import com.scienceminer.utils.FileUtils;
import com.scienceminer.utils.NumCharUtils;
import com.scienceminer.utils.enums.Num;

import java.util.ArrayList;
import java.util.Collections;


public class Day1 {

    private static String INPUTFILE = "/advent2024/inputDay1.txt";

    public static void main(String[] args) {

        ArrayList<String> arrList = FileUtils.readFileToArrayList(INPUTFILE);
        System.out.println(arrList);

        //int count = 0;
        int result = 0;
        ArrayList<Integer> intlist1 = new ArrayList<>();
        ArrayList<Integer> intlist2= new ArrayList<>();

        for (String s : arrList) {

            String firstS = new String(s);
            Integer int1 = new Integer(s.split("   ")[0]);
            Integer int2 = new Integer(s.split("   ")[1]);

            intlist1.add(int1);
            intlist2.add(int2);

            // System.out.println(" Nums are: " + int1 + " and " + int2);
        }

        Collections.sort(intlist1);
        Collections.sort(intlist2);
        int count = 0;
        int totalDifference = 0;
        for (int i : intlist1) {
       //     System.out.println(" First are: " + i);
       //     System.out.println(" Second are: " + intlist2.get(count));
            int difference = i - intlist2.get(count);
            System.out.println(" Diff: " + Math.abs(difference) );
            totalDifference = Math.abs(difference) + totalDifference;
            count++;
        }

        System.out.println(" Total: " +  totalDifference );

        int similarityScore = 0;

        for (int i : intlist1) {
            int appearances = findNumberofTimein(i, intlist2);
            similarityScore = ( appearances * i ) + similarityScore;

        }
        System.out.println(" Similarity: " +  similarityScore );


      //  System.out.println( " RESULT: " + result );

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