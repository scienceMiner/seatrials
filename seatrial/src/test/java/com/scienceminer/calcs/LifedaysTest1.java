package com.scienceminer.calcs;

import org.junit.jupiter.api.*;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class LifedaysTest1 {

    LifeDays ld ;

    @Test
    void name() {
    }

    @BeforeEach
    void setUp() {
        ld = new LifeDays(1930,14, Calendar.AUGUST);

    }

    @BeforeAll
    static void beforeAll() {

    }

    @AfterAll
    static void afterAll() {

    }

    @Test
    @DisplayName("GRC Data -- S.A.D")
    void methodOne () {
        int result = ld.calculate(10, Calendar.MARCH, 2022);
        System.out.println("GRC Final Result:\t" + result);
        assertEquals(33446, result);

    }


    @Test
    @DisplayName("Incorrect GRC Data -- still S.A.D")
    void notCorrect () {
        int result = ld.calculate(10, Calendar.MARCH, 2022);
        System.out.println("GRC Final Result:\t" + result);
        assertNotEquals(1, result);

    }

}
