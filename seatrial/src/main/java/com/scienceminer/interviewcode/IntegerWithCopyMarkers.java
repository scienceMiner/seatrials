package com.scienceminer.interviewcode;

public class IntegerWithCopyMarkers implements Comparable<IntegerWithCopyMarkers> {

    int id;
    int idToMarkCopy;

    public IntegerWithCopyMarkers(int id, int copyId) {
        this.id = id;
        this.idToMarkCopy = copyId;
    }

    public IntegerWithCopyMarkers(int id) {
        this.id = id;
        this.idToMarkCopy = 0;
    }

    public int getId() {
        return id;
    }

    public int getIdToMarkCopy() {
        return idToMarkCopy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerWithCopyMarkers simpson = (IntegerWithCopyMarkers) o;
        return id == simpson.id &&
                idToMarkCopy == simpson.idToMarkCopy;
    }

    @Override
    public int hashCode() {
        return id + idToMarkCopy;
    }

    @Override
    public int compareTo(IntegerWithCopyMarkers integerWithCopyMarkers) {
        if (Integer.compare(id, integerWithCopyMarkers.id) == 0) {
            return Integer.compare(idToMarkCopy, integerWithCopyMarkers.idToMarkCopy);
        } else
            return Integer.compare(id, integerWithCopyMarkers.id);
    }

    @Override
    public String toString() {
        return "(" + this.id + "," + this.idToMarkCopy + ")";
    }

}
