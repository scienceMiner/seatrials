package com.scienceminer.advent2023;


import com.scienceminer.datastructures.LinkedListy;
import com.scienceminer.datastructures.Node;
import com.scienceminer.interviewcode.IntegerWithCopyMarkers;
import com.scienceminer.utils.FileUtils;

import java.util.*;

public class Day4BruteForce {
    private static String INPUTFILE = "/advent2023/inputDay4.txt";
    //private static String INPUTFILE = "/advent2023/day4Test.txt";
    public static void main(String[] args) {

        ArrayList<String> arrList = FileUtils.readFileToArrayList(INPUTFILE);

        Map<Integer, List<Integer>> winMap = new LinkedHashMap<Integer, List<Integer>>();
        Map<Integer, List<Integer>> numMap = new LinkedHashMap<Integer, List<Integer>>();
        Map<Integer, Integer> copies = new LinkedHashMap<>();
        // Map<IntegerWithCopyMarkers, Integer> matchesPerRow = new LinkedHashMap<>();
        Map<IntegerWithCopyMarkers,Integer> matchesPerRow = new TreeMap<IntegerWithCopyMarkers, Integer>() ;

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
                /*
                if (winList.contains(i )) {
                    if (matches == 0)
                        matches = 1;
                    else
                        matches = matches * 2;
                }
                */

            }

            matchesPerRow.put(new IntegerWithCopyMarkers(row),matches);
            process(row,copies,matches);
        }
        calculateResult(matchesPerRow,copies);
        //int sum = getSum(matchesPerRow);
        //System.out.println(" Total : " + sum );
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

    public static LinkedListy<IntegerWithCopyMarkers> toLinkedList(Map<IntegerWithCopyMarkers, Integer > map) {

        LinkedListy<IntegerWithCopyMarkers> iwcmList = new LinkedListy<IntegerWithCopyMarkers>();

        Set<IntegerWithCopyMarkers> intSet = map.keySet();

        for (IntegerWithCopyMarkers iwcm : intSet ) {
            iwcmList.add(new IntegerWithCopyMarkers(iwcm.getId(),map.get(iwcm)));
        }

        return iwcmList;
    }
    public static Integer processForMaxCopy( Map<IntegerWithCopyMarkers, Integer > map,
                                             Integer i ) {
        int copyNum = 0;
        Set<IntegerWithCopyMarkers> intSet = map.keySet();

        for (int copyVal = 1; ; copyVal++) {
        //    System.out.println("check copy for " + i + " coopyVal " + copyVal);
            if (map.containsKey(new IntegerWithCopyMarkers(i,copyVal)) == false) {
                return copyVal-1;
            }
        }
 /*       for (IntegerWithCopyMarkers integerWithCopyMarkers : intSet ) {
            if (integerWithCopyMarkers.getId() == i) {
                if (integerWithCopyMarkers.getIdToMarkCopy() > copyNum)
                    copyNum = integerWithCopyMarkers.getIdToMarkCopy();
            }
        }
*/
      //f  return 0; // copyNum;
    }

    public static Integer   calculateResult( Map<IntegerWithCopyMarkers, Integer > matchesPerRow,
                                              Map<Integer, Integer > copies ) {

        // matchesPerRow: {(0,0)=4, (1,0)=2, (2,0)=2, (3,0)=1, (4,0)=0, (5,0)=0}
        // System.out.println("matchesPerRow: " + matchesPerRow.toString() );
        // System.out.println("copies: " + copies );
        long startTime = System.currentTimeMillis();
        int total = 0;
        int mapSize = matchesPerRow.size();

        // Set<IntegerWithCopyMarkers> intSet = matchesPerRow.keySet();
        LinkedListy<IntegerWithCopyMarkers> iwcmList = toLinkedList(matchesPerRow);

        // (0,4) -> (1,2) -> (2,2) -> (3,1) -> (4,0) -> (5,0)
        // for each element in the list - insert a copy for the next value elements
        // crnt: (0,4) -> move to next element and begin to insert copies
        // THE NEXT ELEMENT MUST HAVE A DIFFERENT X VALUE
        // (0,4) -> (1,2) -> (1,2) -> (2,2) -> (2,2) -> (3,1) -> (3,1) -> (4,0) -> (4,0) -> (5,0)
        // move to next element and repeat
        // crnt: (1,2) -> (so this add a copy of (2,2) and a copy of (3,1)
        // (0,4) -> (1,2) -> (1,2) -> (2,2) -> (2,2) -> (2,2) -> (3,1) -> (3,1) -> (3,1) -> (4,0) -> (4,0) -> (5,0)
        // crnt: (1,2) -> ( this is the copy this time but adds the same copies as above)
        // (0,4) -> (1,2) -> (1,2) -> (2,2) -> (2,2) -> (2,2) -> (2,2) -> (3,1) -> (3,1) -> (3,1) -> (3,1) -> (4,0) -> (4,0) -> (5,0)


        System.out.println(" matchesPerRow: " + matchesPerRow.toString() + " matchesPerRow Size: " + mapSize);
        System.out.println(" LL: " + iwcmList.toString());
        // the iteration is wrong

        Node crnt = iwcmList.getHead();

        while (crnt != null) {
            IntegerWithCopyMarkers crntInt = (IntegerWithCopyMarkers) crnt.getData();
            int copyNum = crntInt.getIdToMarkCopy();
            Node nextDifferentNode = crnt;
            while (copyNum > 0) {
                nextDifferentNode = getNextDifferentNode(nextDifferentNode);
                if (nextDifferentNode != null) {
                    iwcmList.insertCopy(nextDifferentNode);
                }
                // System.out.println("CopyNum: " + copyNum + " Inserting: " + nextDifferentNode.getData().toString() + " after: " + crntInt.toString());
                copyNum--;
            }
            crnt = crnt.getNext();
           // if (iwcmList.length() % 5000 == 0) {
           //     System.out.println("iwcmList: " + iwcmList.length() );
           // }
           long updatedTime = System.currentTimeMillis();
           long diffTime = updatedTime - startTime;
           if (diffTime > 10000) {

               System.out.println("iwcmList: " + iwcmList.length() );
               startTime = updatedTime;
           }
        }

        System.out.println(" LL: " + iwcmList.toString());
        System.out.println(" LL size: " + iwcmList.getSize());

        return 0;
    }

    public static Node getNextDifferentNode(Node crntNode) {
        IntegerWithCopyMarkers iwcm = (IntegerWithCopyMarkers) crntNode.getData();
        Node nextNode = crntNode.getNext();
        while (nextNode != null) {
                IntegerWithCopyMarkers iwcmToFind = (IntegerWithCopyMarkers) nextNode.getData();
                if (iwcmToFind.getId() != iwcm.getId()) {
                    // System.out.println(" iwcmToFind id: " + iwcmToFind.getId() + " vs. iwcm id: " + iwcm.getId() );
                    return nextNode; // this is a different Node
                }
                else
                    nextNode = nextNode.getNext();
        }
        return nextNode; // must be null
    }

    public static Integer calculateOldResult( Map<IntegerWithCopyMarkers, Integer > matchesPerRow,
                                        Map<Integer, Integer > copies )  {

    // matchesPerRow: {(0,0)=4, (1,0)=2, (2,0)=2, (3,0)=1, (4,0)=0, (5,0)=0}
    // System.out.println("matchesPerRow: " + matchesPerRow.toString() );
    // System.out.println("copies: " + copies );

        int total = 0;
        int mapSize = matchesPerRow.size();

       // Set<IntegerWithCopyMarkers> intSet = matchesPerRow.keySet();
        LinkedListy<IntegerWithCopyMarkers> iwcmList = toLinkedList(matchesPerRow);


        System.out.println(" matchesPerRow: " + matchesPerRow.toString() + " matchesPerRow Size: " + mapSize );
        System.out.println(" LL: " + iwcmList.toString());
        // the iteration is wrong
        for ( int i = 0 ; i < mapSize ; i++ ) {

            if (mapSize % 4000 == 0) {
                System.out.println("Mapsize: " + mapSize );
            }
            //  Convert keys to a List
            List<IntegerWithCopyMarkers> keyList = new ArrayList<>(matchesPerRow.keySet());
            // Access key at index 1
            IntegerWithCopyMarkers keyAtIndexi = keyList.get(i);
    //        System.out.println("CRNT matchesPerRow: " + matchesPerRow.toString()  );
    //          System.out.println("BEGIN COPY CHECK FOR: " + keyAtIndexi.toString() );

            //System.out.println("Key at index 1: " + keyAtIndex1);

            // Integer matches = matchesPerRow.get(new IntegerWithCopyMarkers(i));
            Integer matches = matchesPerRow.get(keyAtIndexi);


            if (matches != null ) {
    //            System.out.println("ROW: " + i + " and matches " + matches);

                // int copyNum = copies.get(i).intValue();
                //  int copyNum = copies.get(i);
                int posToAdd = keyAtIndexi.getId()+1; // begin insertion after current i
                for (int c = 1; c <= matches; c++) {
                    // for each copy add a new entry to matchesPerRow
                    int findMaxCopy = processForMaxCopy(matchesPerRow, posToAdd);
                    int key = posToAdd;
     //               System.out.println("MAX COPY for key: " + key + " IS: " + findMaxCopy);

                    int value = matchesPerRow.get(new IntegerWithCopyMarkers(posToAdd, 0));
                   // for (int v = 1; v <= value; v++) {
                    matchesPerRow.put(new IntegerWithCopyMarkers( posToAdd, findMaxCopy+1), value);
                        //   System.out.println(" Adding: " + c + " with matches " + matches );
                    mapSize = matchesPerRow.size();

                    posToAdd++;
                   // }
                }

            }


        }

        return total;

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