package com.scienceminer.utils.enums;

public enum Num {

    ONE("one"), TWO("two"), THREE("three"), FOUR("four"), FIVE("five"),
    SIX("six"), SEVEN("seven"), EIGHT("eight"), NINE("nine") ;

    private String number;

    private Num(String nu) {
        this.number = nu;
    }
    public String getNum() {
        return this.number;
    }
}

