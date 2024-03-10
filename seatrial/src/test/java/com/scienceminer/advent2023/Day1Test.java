package com.scienceminer.advent2023;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {

    Day1 d1;
    @BeforeEach
    void setUp() {
        //d1 = new Day1(); // foolish why did I create with
        // Car c1 - so dumb so dumb so dumb so dumb so dumb
    }

    @Test
    void testConvert() {
        String num1 = "eight54nine";
        String result = Day1.convert(num1);
        String target = "8549";
        System.out.println(result);
        assertEquals(result,target);
    }


    @Test
    void testConvert2() {
        String num1 = "blrlnfnnvbhkltmgqqsixsix2bdnprfp2rpqtnvbtwonenpx";
        String result = Day1.convert(num1);
        String target = "blrlnfnnvbhkltmgqq662bdnprfp2rpqtnvbtw1npx";
        System.out.println(result);
        assertEquals(result,target);
    }
}
