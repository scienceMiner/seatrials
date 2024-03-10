package com.scienceminer.utils;

public class Coordinate {
    int x;
    int y;

    Coordinate() {
        x = 0;
        y = 0;
    }

    public Coordinate(int lx, int ly) {
        x = lx;
        y = ly;
    }

    private int extractDigit(String str) {
        String num = str.replaceAll("[^\\d]", "");
        return Integer.parseInt(num);
    }
    public void setX(int lx) {
        x = lx;
    }
    public void setY(int ly) {
        y = ly;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return new String("(X:" + getX() + ",Y:" + getY() + ")");
    }
    @Override
    public boolean equals(Object obj) {
        Coordinate s = (Coordinate)obj;
        return this.x == s.x && this.y == s.y ;
    }

    @Override
    public int hashCode() {
        return this.getX() * 31 + this.getY();
    }
}