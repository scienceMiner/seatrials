package com.scienceminer.advent2023;


import com.scienceminer.interviewcode.IntegerWithCopyMarkers;
import main.java.com.scienceminer.utils.FileUtils;

import java.util.*;

public class Day4 {
    private static String INPUTFILE = "/advent2023/inputDay4.txt";
    //private static String INPUTFILE = "/advent2023/day4Test.txt";
    public static void main(String[] args) {

        ArrayList<String> arrList = FileUtils.readFileToArrayList(INPUTFILE);

        Map<Integer, List<Integer>> winMap = new LinkedHashMap<Integer, List<Integer>>();
        Map<Integer, List<Integer>> numMap = new LinkedHashMap<Integer, List<Integer>>();
        Map<Integer, Integer> copies = new LinkedHashMap<>();
        Map<Integer,IntegerWithCopyMarkers> matchesPerRow = new TreeMap<Integer,IntegerWithCopyMarkers>() ;

        parseInputToMap(arrList, winMap, numMap, copies);


        int total1 = 0; // calculateTimesAnswer(borderListMap);

        System.out.println( " winMap " + winMap.toString());
        System.out.println( " numMap " + numMap.toString());

        int total = 0;

        for ( int row : winMap.keySet() ) {
            int matches = 0;
            List<Integer> numList = numMap.get(row);
            List<Integer> winList = winMap.get(row);
            for ( Integer i : numList ) {

                if (winList.contains(i)) {
                    matches = matches + 1;
                }

            }

            matchesPerRow.put(row, new IntegerWithCopyMarkers(matches,1));
            process(row,copies,matches);
        }

        System.out.println(" matchesPerRow: " + matchesPerRow.toString() + " matchesPerRow Size: " + matchesPerRow.size() );

        calculateResult(matchesPerRow,copies);

        System.out.println(" matchesPerRow: " + matchesPerRow.toString() + " matchesPerRow Size: " + matchesPerRow.size() );
        System.out.println(" matchesPerRow.size() : " + matchesPerRow.size() );
    }

    public static void process( int row, Map<Integer, Integer > copies, int matches) {
        // if row = 1, matches = 4, add 1 to each of the copies entries for 2,3,4,5
        // if row = 2, matches = 2, add 1 to each of the copies for 3,4
        for (int i = 0 ; i < matches; i++ ) {
            row = row + 1;
            int currentCopySizeForRow = copies.get(row);
            copies.replace(row,currentCopySizeForRow+1);
        }   

        // so
    }

    /*

Card 1 has four matching numbers, so you win one copy each of the next four cards: cards 2, 3, 4, and 5.
Your original card 2 has two matching numbers, so you win one copy each of cards 3 and 4.
Your copy of card 2 also wins one copy each of cards 3 and 4.
Your four instances of card 3 (one original and three copies) have two matching numbers, so you win four copies each of cards 4 and 5.
Your eight instances of card 4 (one original and seven copies) have one matching number, so you win eight copies of card 5.
Your fourteen instances of card 5 (one original and thirteen copies) have no matching numbers and win no more cards.
Your one instance of card 6 (one original) has no matching numbers and wins no more cards.

    winMap, numMap, matchesPerRow

    NEED A HASH COPY STRUCTURE (HCS)
    1 -> 4 matches, 2 -> 2 matches, 3 -> 2 matches , 4 -> 1 match, 5 -> 0 matches , 6 -> 0 matches
    Start at 1 -> iterate for 4 matches
    HCS IS : 1 -> 4 matches , 2 -> 2 matches(1st), 2 -> 2 matches(2nd), 3 -> 2 matches(1st), 3 -> 2 matches(2nd), 4 -> 1 match, 4 -> 1 match,
             5 -> 0 matches, 5 -> 0 matches, 6 -> 0 matches
    Now at 2 -> 2 matches (first) so 3 and 4
    HCS IS : 1 -> 4 matches , 2 -> 2 matches(1st), 2 -> 2 matches(2nd), 3 -> 2 matches(1st),
             3 -> 2 matches(2nd), 3 -> 2 matches(3rd),
             4 -> 1 match (1st), 4 -> 1 match (2nd), 4-> 1 match(3rd)
             5 -> 0 matches, 5 -> 0 matches, 6 -> 0 matches
    Now at 2 -> 2 matches (second) so 3 and 4
    HCS IS : 1 -> 4 matches , 2 -> 2 matches(1st), 2 -> 2 matches(2nd),
             3 -> 2 matches(1st), 3 -> 2 matches(2nd), 3 -> 2 matches(3rd), 3 -> 2 matches (4th)
             4 -> 1 match (1st), 4 -> 1 match (2nd), 4-> 1 match(3rd), 4 -> 1 match (4th)
             5 -> 0 matches, 5 -> 0 matches, 6 -> 0 matches
    Now at 3 -> 2 matches (1st) so 4 and 5
    HCS IS : 1 -> 4 matches , 2 -> 2 matches(1st), 2 -> 2 matches(2nd),
             3 -> 2 matches(1st), 3 -> 2 matches(2nd), 3 -> 2 matches(3rd), 3 -> 2 matches (4th)
             4 -> 1 match (1st), 4 -> 1 match (2nd), 4-> 1 match(3rd), 4 -> 1 match (4th)
             5 -> 0 matches, 5 -> 0 matches, 6 -> 0 matches


    */

    public static Integer getSum( Map<IntegerWithCopyMarkers, Integer > map) {
        int sum = 0;
        Set<IntegerWithCopyMarkers> intSet = map.keySet();

        for (IntegerWithCopyMarkers integerWithCopyMarkers : intSet ) {
            sum = map.get(integerWithCopyMarkers) + sum;
        }

        return sum;
    }

    public static Integer calculateResult( Map<Integer, IntegerWithCopyMarkers > matchesPerRow,
                                        Map<Integer, Integer > copies )  {

    // matchesPerRow: {(0,0)=4, (1,0)=2, (2,0)=2, (3,0)=1, (4,0)=0, (5,0)=0}

        int total = 0;

        Set<Integer> intSet = matchesPerRow.keySet();

        int totalVal = 0;
        for ( Integer position : intSet ) {

            IntegerWithCopyMarkers iwcm = matchesPerRow.get(position);
            int currentNumberOfCopies = iwcm.getIdToMarkCopy();
            int numberToCopy = iwcm.getId();

           for (int i = 1 ; i <= numberToCopy ; i++) {
                IntegerWithCopyMarkers iwcmLocal = matchesPerRow.get(position+i);
                matchesPerRow.replace(position+i, new IntegerWithCopyMarkers(iwcmLocal.getId(),iwcmLocal.getIdToMarkCopy()+currentNumberOfCopies));
            }
            //   System.out.println(" matchesPerRow: " + matchesPerRow.toString() + " matchesPerRow Size: " + matchesPerRow.size() );

            IntegerWithCopyMarkers iMarkersToAdd = ((IntegerWithCopyMarkers) matchesPerRow.get(position));
            totalVal = totalVal + iMarkersToAdd.getIdToMarkCopy();
        }

        System.out.println("Total: " + totalVal );

        return 0;

    }

    public static void parseInputToMap(ArrayList<String> arrList,
                                        Map<Integer, List<Integer>> winMap,
                                        Map<Integer, List<Integer>> numMap,
                                        Map<Integer, Integer> copies ) {

        int row = 0;
        StringBuilder sb = new StringBuilder();
        for (String s : arrList) {

            String[] splitStringArray = s.split(":");

            //  int row = getRowNum(splitStringArray[0]);
            String[] nums = splitStringArray[1].split("\\|" );
            Scanner scanner = new Scanner(nums[0]);
            ArrayList<Integer> myInts = new ArrayList<Integer>();
            while (scanner.hasNextInt()) {
                myInts.add(scanner.nextInt());
            }

            winMap.put(row,myInts);

            scanner = new Scanner(nums[1]);
            ArrayList<Integer> myInts2 = new ArrayList<Integer>();
            while (scanner.hasNextInt()) {
                myInts2.add(scanner.nextInt());
            }

            numMap.put(row,myInts2);

            copies.put(row,1);
           row++;
        }



    }




}