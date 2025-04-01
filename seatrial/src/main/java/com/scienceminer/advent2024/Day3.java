package com.scienceminer.advent2024;


import com.scienceminer.utils.Coordinate;
import com.scienceminer.utils.FileUtils;
import com.scienceminer.utils.NumCharUtils;
import com.scienceminer.utils.enums.Num;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Day3 {

    private static String INPUTFILE = "/advent2024/inputDay3.txt";

    public static void main(String[] args) {

        ArrayList<String> arrList = FileUtils.readFileToArrayList(INPUTFILE);
        System.out.println(arrList.size());

        parseInputToMapDO(arrList);


    // im getting 529 but the correct answer is 528 ????

    }

    public static void parseInputToMapDO(ArrayList<String> arrList) {
        int count = 1;
        int row = 0;
        ArrayList<String> multiplyStrings = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        for (String s : arrList) {

            int column = 0;
            int stringLength = s.length();
            int charpos = 1;
            for (char c : s.toCharArray()) {
                //  System.out.println("c: " + c);
                {
                    // begin parse
                    if (NumCharUtils.isDigit(c) || validChar(c) || validCharDo(c) || validCharDont(c)) {
                        //       System.out.println("valid");
                        if (sb.length() == 0 && (c == 'm' || c == 'd') )
                            sb.append(c);
                        else if (sb.length() != 0)
                            sb.append(c);
                        if (c == ')') {
                      //      System.out.println(" TESTING: " + sb.toString() );
                            if (testCharacters("mul(,)", sb.toString()))
                                multiplyStrings.add(sb.toString());
                            if (testCharactersDo("do()", sb.toString()))
                                multiplyStrings.add(sb.toString());
                            if (testCharactersDont("don't()", sb.toString()))
                                multiplyStrings.add(sb.toString());
                            sb = new StringBuilder();
                        }
                    } else {
                        // not valid char
                        if (sb.length() != 0) {
                            //           System.out.println(" add to sb");
                       //     System.out.println(" TESTING: " + sb.toString() );
                            if (testCharacters("mul(,)", sb.toString()))
                                multiplyStrings.add(sb.toString());
                            if (testCharactersDo("do()", sb.toString()))
                                multiplyStrings.add(sb.toString());
                            if (testCharactersDont("don't()", sb.toString()))
                                multiplyStrings.add(sb.toString());
                            sb = new StringBuilder();
                        }
                        // do nothing
                    }

                }

            }

        }

        BigDecimal total = BigDecimal.ZERO;
        boolean on = true;
        for (String s : multiplyStrings ) {
            System.out.println(" OUTPUT: " + s );
            if (s.equals("do()")) {
                System.out.println(" IN DO: " );
                on = true;
            }
            else if (s.equals("don't()")) {
                System.out.println(" IN DON'T: " );
                on = false;
            }
            else {
                if (on) {
                    List<BigDecimal> bigDecimalList = extractInteger("\\d+", s);
                    //BigDecimal dec2 = extractInteger("mul\\(.*?,[0-9]*\\)",s);
                    System.out.println(s + " VALUE 1: " + bigDecimalList.toString() + " VALUE 2: ");
                    BigDecimal multiplicand = bigDecimalList.get(0).multiply(bigDecimalList.get(1));
                    System.out.println(s + " VALUE 1: " + multiplicand);

                    total = total.add(multiplicand);
                }
            }
        }

        System.out.println(" SIZE: " + multiplyStrings.size() );
        System.out.println(" total: " + total );
    }

    public static void parseInputToMap(ArrayList<String> arrList) {
        int count = 1;
        int row = 0;
        ArrayList<String> multiplyStrings = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        for (String s : arrList) {

            int column = 0;
            int stringLength = s.length();
            int charpos = 1;
            for (char c : s.toCharArray()) {
                //  System.out.println("c: " + c);
                    {
                        // begin parse
                        if (NumCharUtils.isDigit(c) || validChar(c)) {
                            //       System.out.println("valid");
                            if (sb.length() == 0 && c == 'm')
                                sb.append(c);
                            else if (sb.length() != 0)
                                sb.append(c);
                            if (c == ')') {
                                System.out.println(" TESTING: " + sb.toString() );
                                if (testCharacters("mul(,)", sb.toString()))
                                    multiplyStrings.add(sb.toString());
                                sb = new StringBuilder();
                            }
                        } else {
                            // not valid char
                            if (sb.length() != 0) {
                                //           System.out.println(" add to sb");
                                System.out.println(" TESTING: " + sb.toString() );
                                if (testCharacters("mul(,)", sb.toString()))
                                    multiplyStrings.add(sb.toString());
                                sb = new StringBuilder();
                            }
                            // do nothing
                        }

                    }

                }

        }

        BigDecimal total = BigDecimal.ZERO;

        for (String s : multiplyStrings ) {
            List<BigDecimal> bigDecimalList = extractInteger("\\d+",s);
            //BigDecimal dec2 = extractInteger("mul\\(.*?,[0-9]*\\)",s);
            System.out.println(s + " VALUE 1: " + bigDecimalList.toString() + " VALUE 2: " );
            BigDecimal multiplicand = bigDecimalList.get(0).multiply(bigDecimalList.get(1));
            System.out.println(s + " VALUE 1: " + multiplicand );

            total = total.add(multiplicand);
        }

        System.out.println(" SIZE: " + multiplyStrings.size() );
        System.out.println(" total: " + total );
    }

    public static List<BigDecimal> extractInteger( String regex, String multiplyStrings ) {
        List<BigDecimal> bigDecimals = new ArrayList<BigDecimal>();
        String s1 = "some string with 'the data i want' inside";
        Pattern pattern = Pattern.compile( regex );
        Matcher matcher = pattern.matcher(multiplyStrings);
        while (matcher.find())
        {
            bigDecimals.add(new BigDecimal(matcher.group()) );
           // return new BigDecimal(matcher.group());
        }

        return bigDecimals;
    }
    public static boolean validChar(char c ) {
        String key = "mul(,)";

        if (key.contains(Character.toString(c)))
            return true;
        else
            return false;
    }

    public static boolean validCharDo(char c ) {
        String key = "do()";

        if (key.contains(Character.toString(c)))
            return true;
        else
            return false;
    }

    public static boolean validCharDont(char c ) {
        String key = "don't()";

        if (key.contains(Character.toString(c)))
            return true;
        else
            return false;
    }

    public static boolean testCharactersDo(String testDo, String stringToTest ) {

        Pattern p = Pattern.compile( "do\\(\\)" );
        Matcher m = p.matcher(stringToTest);

        if ( m.find() ) {
            return true;
        }
        else
            return false;
    }

    public static boolean testCharactersDont(String testDont, String stringToTest ) {

        Pattern p = Pattern.compile( "don't\\(\\)" );
        Matcher m = p.matcher(stringToTest);

        if ( m.find() ) {
            return true;
        }
        else
            return false;
    }


    public static boolean testCharacters(String testSetOfChars, String stringToTest ) {
/*
        Set<Character> charsToTestFor = testSetOfChars.chars()
                .mapToObj(ch -> Character.valueOf((char) ch))
                .collect(Collectors.toSet());

        boolean allCharInString = stringToTest.chars()
                .allMatch(ch -> charsToTestFor.contains(Character.valueOf((char) ch)));
       // System.out.println("Does " + stringToTest + " contain any of " + charsToTestFor + "? " + anyCharInString);
       return !allCharInString;

*/
        Pattern p = Pattern.compile( "mul\\([0-9]*,[0-9]*\\)" );
        Matcher m = p.matcher(stringToTest);

        if ( m.find() ) {

            //  if (!stringToTest.contains("mul(") & !stringToTest.matches("mul(*,*)"))
            return true;
        }
        else
            return false;

    }

}