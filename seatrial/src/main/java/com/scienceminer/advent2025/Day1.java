package com.scienceminer.advent2025;


import com.scienceminer.utils.FileUtils;
import com.scienceminer.utils.NumCharUtils;
import com.scienceminer.utils.enums.Num;

import java.util.ArrayList;
import java.util.Collections;

/*

        dial has 99 slots.
        The dial starts by pointing at 50.
        The dial is rotated L68 to point at 82.
        The dial is rotated L30 to point at 52.
        The dial is rotated R48 to point at 0.
The dial is rotated L5 to point at 95.
The dial is rotated R60 to point at 55.
The dial is rotated L55 to point at 0.
The dial is rotated L1 to point at 99.
The dial is rotated L99 to point at 0.
The dial is rotated R14 to point at 14.
The dial is rotated L82 to point at 32.

        current = 50
        L68 -> current = -18 - reassign to 100-18 = 82
        L30 -> current = 52
        R48 -> current = 100 - reassign to 100-100 = 0
        L5 -> current = 0-5 - reassign to 100 - 5 = 95
        R650 -> current = 745 - keep reassign to 745-100 = 45 until less than 100
        L55 - > current = 0
 */

public class Day1 {

    private static String INPUTFILE = "/advent2025/inputDay1.txt";

    private static Integer totalCount = 0;
    public static void main(String[] args) {

        ArrayList<String> arrList = FileUtils.readFileToArrayList(INPUTFILE);
        System.out.println(arrList);

        Integer zeroCount = 0;
        Integer start = 50;
      //  System.out.println( " RESULT: " + result );
        for ( String entry : arrList ) {
            char c = entry.charAt(0);
            try {
                Integer num = Integer.parseInt(entry.substring(1));

                start = Day1.process(start, c, num);

              //  if ( position == 0 )
                    System.out.println(" NUM: " + num + " current " + start );

                if (start == 0 ) {
                    zeroCount++;
                }
            }
            catch (NumberFormatException ex) {
                System.out.println(" Cannot parse " + ex.toString() );
            }
        }

        System.out.println(" ZEROS: " + zeroCount );
        System.out.println(" TOTAL ZEROS PASSED: " + totalCount );


    }

    public static int process ( Integer start, char c, Integer num ) {

        Integer val = 0;
        if (c == 'L') {
            val = start - num;
            while ( val < 0 ) {
                System.out.println("L cross zero ");
                totalCount++;
                val = 100 + val;
            }
        }

        if (c == 'R') {
            val = start + num;
            while ( val >= 100 ) {
                System.out.println("R cross zero ");
                totalCount++;
                val = val - 100;
            }
        }

        return val;

    }





}