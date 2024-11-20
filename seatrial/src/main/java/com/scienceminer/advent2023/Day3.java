package com.scienceminer.advent2023;


import com.scienceminer.utils.Coordinate;
import com.scienceminer.utils.NumCharUtils;
import com.scienceminer.utils.FileUtils;

import java.util.*;

/*
467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..

(0,0) 467 (0,2) -> so for -1,-1 to -1,3 -> part ?  and for 0,-1 and for 0,3 and for 1,-1 to 1,3 -> part ?
(0,5) 114 (0,7)
(1,3) * (1,3)
*/

public class Day3 {

    private static String INPUTFILE = "/advent2023/inputDay3.txt";
    //private static String INPUTFILE = "/advent2023/day3Test.txt";
    public static void main(String[] args) {

        ArrayList<String> arrList = FileUtils.readFileToArrayList(INPUTFILE);

        Map<Coordinate, String> partMap = new LinkedHashMap<Coordinate, String>();
        Map<Coordinate, String> numMap = new LinkedHashMap<Coordinate, String>();

        parseInputToMap(arrList, partMap, numMap);

        Map<Integer, List<Coordinate>> borderMap = createBorderMap(numMap);
        int total = calculateTotal(partMap, borderMap);
      //  formGearRatios(numMap,partMap);
        System.out.println(" Total : " + total );

        Map<Coordinate, List<Coordinate>> numberBorderMap = createNumberBorderMap(numMap);

        System.out.println(" numberBorderMap : " + numberBorderMap.toString() );

        // create a border Map for each * symbol where the key is coordinate for the border - this is unique
        Map<Coordinate, List<Coordinate>> borderMapForParts = createPartBorderMap(partMap);

        System.out.println(" borderMapForParts : " + borderMapForParts.toString() );

        // for each number in number map where the coordinate is the key -
        Map<Coordinate, List<Integer> >  borderListMap = createBorderTimesListMap(numMap,numberBorderMap,borderMapForParts);
        //   if it in the border of a * add the number to the borderTimesList for the part - where the key is the coordinate
        // for each entry in the borderTimes list - if there are 2 entries - calculate the multiplication , add to total
        System.out.println(" borderListMap : " + borderListMap.toString() );

        for (Coordinate c : borderListMap.keySet() ){
            System.out.println(c.toString());
            List<Integer> li = borderListMap.get(c);
            System.out.println(li.toString());
        }

        int total1 = calculateTimesAnswer(borderListMap);

        System.out.println(" Total : " + total1 );
    }

    public static void parseInputToMap(ArrayList<String> arrList, Map<Coordinate, String> partMap, Map<Coordinate, String> numMap) {
        int count = 1;
        int row = 0;
        StringBuilder sb = new StringBuilder();
        for (String s : arrList) {

            int column = 0;
            int stringLength = s.length();
            int charpos = 1;
            for (char c : s.toCharArray()) {
                //  System.out.println("c: " + c);
                if (charpos == stringLength ) {
                    if (NumCharUtils.isDigit(c)) {
                        sb.append(c);
                    }
                    if (sb.length() != 0) {
                        String numString = sb.toString();
                        sb = new StringBuilder();
                        if (c =='.')
                            column = column;
                        if (NumCharUtils.isDigit(c))
                            column = column+1;
                        int columnValAtStart = column-(numString.length());
                        numMap.put(new Coordinate(row, columnValAtStart), numString);
                        //System.out.println(" CHARMATCH num at (" + row + "," + columnValAtStart + ") is: " + numString);

                    }
                }
                else if (!NumCharUtils.isDigit(c) && c != '.') {
                    partMap.put(new Coordinate(row, column), String.valueOf(c));
                    //  System.out.println(" part at (" + row + "," + column + ") is: " + String.valueOf(c));
                    if (sb.length() != 0) {
                        String numString = sb.toString();
                        sb = new StringBuilder();
                        int columnValAtStart = column-(numString.length());
                        numMap.put(new Coordinate(row, columnValAtStart), numString);
                        //    System.out.println(" num at (" + row + "," + columnValAtStart + ") is: " + numString);

                    }
                } else if (!NumCharUtils.isDigit(c) || c == '.' ) {
                    if (sb.length() != 0) {
                        String numString = sb.toString();
                        sb = new StringBuilder();
                        int columnValAtStart = column-(numString.length());
                        numMap.put(new Coordinate(row, columnValAtStart), numString);
                        //    System.out.println(" num at (" + row + "," + columnValAtStart + ") is: " + numString);

                    }
                } else if (NumCharUtils.isDigit(c)) {
                    sb.append(c);
                } else if (c == '\n') {
                    if (sb.length() != 0) {
                        String numString = sb.toString();
                        sb = new StringBuilder();
                        int columnValAtStart = column-(numString.length());
                        numMap.put(new Coordinate(row, columnValAtStart), numString);
                        //    System.out.println(" num at (" + row + "," + columnValAtStart + ") is: " + numString);

                    }
                }
                column++;
                charpos++;
            }
            row++;
            count++;
        }

    }

    public static Map<Coordinate, List<Coordinate>> createPartBorderMap(Map<Coordinate,String> partMap) {

        Map<Coordinate, List<Coordinate>> rangeMap = new LinkedHashMap<>();
        for (Coordinate ep : partMap.keySet()) {
            String part = partMap.get(ep);
            // only for
            if (part.equals("*")) {
                int partLength = part.length(); // always 1 for a part
                List rangeList = createBorderForPart(ep, partLength);
                rangeMap.put(ep, rangeList);
            }
        }

        return rangeMap;
        // now have the border for each number

    }

    public static Map<Integer, List<Coordinate>> createBorderMap(Map<Coordinate,String> numsMap) {
        Map<Integer, List<Coordinate>> rangeMap = new LinkedHashMap<>();
        for (Coordinate ep : numsMap.keySet()) {
            String num = numsMap.get(ep);
            int numberOfDigits = num.length(); // always 1 for a part
            List rangeList = createBorderForPart(ep,numberOfDigits);

                Integer number = Integer.valueOf(num);
                // if the number exists just add the new Rangelist ???
                if (rangeMap.containsKey(number)) {
                    List<Coordinate> oldRangeList = rangeMap.get(number);
                    oldRangeList.addAll(rangeList);
                }
                else
                    rangeMap.put(number,rangeList);
        }

        return rangeMap;
        // now have the border for each number

    }

    public static Map<Coordinate, List<Coordinate>> createNumberBorderMap(Map<Coordinate,String> numsMap) {
        Map<Coordinate, List<Coordinate>> rangeMap = new LinkedHashMap<>();
        for (Coordinate ep : numsMap.keySet()) {
            String num = numsMap.get(ep);
            int numberOfDigits = num.length(); // always 1 for a part
            List rangeList = createBorderForPart(ep,numberOfDigits);

            rangeMap.put(ep,rangeList);
        }

        return rangeMap;

    }

    public static int calculateTimesAnswer( Map<Coordinate, List<Integer> > nearPieces ) {
        int total = 0;
        for (Coordinate c : nearPieces.keySet()) {
            List<Integer> numList = nearPieces.get(c);
            total = total + multipleItemIfSize2(numList);
        }
        return total;
    }

    public static int multipleItemIfSize2(List<Integer> numlist) {
        if (numlist.size() != 2)
            return 0;
        else {
            return numlist.get(0) * numlist.get(1);
        }

    }

    public static int calculateTotal(Map<Coordinate,String> partMap, Map<Integer, List<Coordinate>> rangeMap ) {
        int total = 0;
        // if a number touches a part then we add it
        Set<Coordinate> symbolLocations = partMap.keySet();
        for (Integer num : rangeMap.keySet()) {
            //    System.out.println(" checking for " + num );
            List<Coordinate> borderForNumber = rangeMap.get(num);
            //   System.out.print(" border:  " );
            for (Coordinate engp : borderForNumber ) {
                //        System.out.print(" ::" + engp.toString() + " ");
            }
            //    System.out.println();
            boolean found = false;
            for (Coordinate symbolLocation : symbolLocations) {
                //System.out.println(" PART LOCATION: " + symbolLocation );
                if (borderForNumber.contains(symbolLocation)) {
                    found = true;
                    total = total + num;
                    //           System.out.println(" Num is next to Symbol Location " + symbolLocation.toString() + " ");
                }
            }
            if (found) {
                // total = total + num;
            }
        }

        return total;
    }
    public static List createBorderForPart(Coordinate ep, int numberLength) {
        List rangeList = new LinkedList<Coordinate>();
        int x = ep.getX();
        int y = ep.getY();

        // add above - does it matter if the numbers are -ve ? no

        int startX = x-1;
        int startY = y-1;
        int endY = y+numberLength;
        for (int i = startY ; i <= endY ; i++ ) {
            rangeList.add(new Coordinate(startX,i));
        }

        // add before same row, add after same row
        startY = y-1;
        endY = y+numberLength;
        rangeList.add(new Coordinate(x,startY));
        rangeList.add(new Coordinate(x,endY));

        // add after same row
        startX = x+1;
        startY = y-1;
        endY = y+numberLength;
        for (int i = startY ; i <= endY ; i++ ) {
            rangeList.add(new Coordinate(startX,i));
        }

        return rangeList;
    }


    public static Map<Coordinate, List<Integer>> createBorderTimesListMap( Map<Coordinate, String> numMap,
                                                                Map<Coordinate, List<Coordinate>> numberBorderMap,
                                                                Map<Coordinate, List<Coordinate>> borderMapForMultiplicationSymbol ) {

        // coordinate is the coordinate of a part

       // Map<Coordinate, List<Integer>> borderTimesListMap = new LinkedHashMap<Coordinate, List<Integer> >();
        Map<Coordinate, List<Integer>> borderTimesListMap = new LinkedHashMap<>();

        // for each number - get the border map
        // if any entry in the border map is the coordinate for a part
        //   add the number to the

        for (Coordinate coord : numMap.keySet() ) {
            Integer number = Integer.valueOf(numMap.get(coord));
            List<Coordinate> numberBorderList = numberBorderMap.get(coord);

            for (Coordinate numberBorderCoord : numberBorderList) {
          //          System.out.print(" ::  " + numberBorderCoord.toString() );
                //

                if (borderMapForMultiplicationSymbol.containsKey(numberBorderCoord)) {
                    // number is in the border of a multiplication symbol
                    if (borderTimesListMap.containsKey(numberBorderCoord)) {
                        System.out.print(" A * border contains: " + numberBorderCoord.toString() );
                        System.out.print(" and the coord is : " + coord.toString() );
                        System.out.print(" CURRENT borderTimesListMap: " + borderTimesListMap.toString() );
                        List<Integer> listInt2 = borderTimesListMap.get(numberBorderCoord);
                        if (listInt2 == null) {
                            listInt2 = new LinkedList<Integer>();
                        }
                        listInt2.add(number);
                        System.out.println(" with number: " + listInt2.toString() );

                    } else {
                        System.out.println(" Adding: " + number + " to a new list for " + numberBorderCoord.toString() );

                        List<Integer> listInt = new LinkedList<Integer>();
                        listInt.add(number);
                        borderTimesListMap.put(numberBorderCoord, listInt);
                    //    System.out.println(" Adding : " + number + " for coord " + numberBorderCoord );
                        List<Integer> li2 = borderTimesListMap.get(numberBorderCoord);
                        System.out.println(" li2 : " + li2.toString() );
                    }
                }
                else {
               //     System.out.print(" DOES NOT CONTAIN ::  " + borderMapForMultiplicationSymbol.toString() );
               //     System.out.println(" NOT FOUND: " + numberBorderCoord.toString());

                }
            }

        }

        return borderTimesListMap;
    }


    public static void formGearRatios(Map<Coordinate,String> numsMap, Map<Coordinate,String> partMap ) {
            for (Coordinate ep : partMap.keySet()) {
                String part = partMap.get(ep);
                if ( part.equals("*"))
                    System.out.println(ep.toString() + " part: " + part );
            }
        }

}