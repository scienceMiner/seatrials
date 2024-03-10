package com.scienceminer.interviewcode;

import java.util.LinkedList;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class RefTest {


    public static void main(String[] args ) {


        SortedMap<IntegerWithCopyMarkers, String> map = new TreeMap();

        map.put(new IntegerWithCopyMarkers(10, 0), "first");
        map.put(new IntegerWithCopyMarkers(22, 0), "second");
        map.put(new IntegerWithCopyMarkers(22, 1), "second second");
        map.put(new IntegerWithCopyMarkers(33, 0), "third");
        map.put(new IntegerWithCopyMarkers(44, 0), "fourth");
        map.put(new IntegerWithCopyMarkers(22, 2), "second second second");
        map.put(new IntegerWithCopyMarkers(55, 0), "fifth");

        Set<IntegerWithCopyMarkers> keys = map.keySet();
        //Integer[] arr = keys.toArray(new Integer[0]);

        int mapSize = map.size();
        for ( int i = 0 ; i < mapSize ; i++ ) {
            Set<IntegerWithCopyMarkers> intSet = map.keySet();
            IntegerWithCopyMarkers position = ( (IntegerWithCopyMarkers) intSet.toArray()[i]);
            System.out.println( position.toString() + " VALUE: " +  map.get( position )  );
            if ( position.getId() >= 10 && position.getId() < 20 ) {
                if (map.containsKey( new IntegerWithCopyMarkers(11) ) == false ) {
                    System.out.println("add sixth ");
                    map.put( new IntegerWithCopyMarkers(11 ), "sixth");
                }
            }
            mapSize = map.size();
        }

        System.out.println( " MAP>>>>>> "  );

        for ( int i = 0 ; i < mapSize ; i++ ) {
            Set<IntegerWithCopyMarkers> intSet = map.keySet();
            IntegerWithCopyMarkers position = ( (IntegerWithCopyMarkers) intSet.toArray()[i]);
            System.out.println( position.toString() + " VALUE: " +  map.get( position )  );
        }

        LinkedList<IntegerWithCopyMarkers> iwcList = new LinkedList<>();
        iwcList.add(new IntegerWithCopyMarkers(10, 0) );
        iwcList.add(new IntegerWithCopyMarkers(22, 0) );
        iwcList.add(new IntegerWithCopyMarkers(22, 1) );
        iwcList.add(new IntegerWithCopyMarkers(33, 0) );
        iwcList.add(new IntegerWithCopyMarkers(44, 0) );
        iwcList.add(new IntegerWithCopyMarkers(22, 2) );
        iwcList.add(new IntegerWithCopyMarkers(55, 0) );

    /*    IntegerWithCopyMarkers iwcRef = iwcList.getFirst();
        iwcList.push();
        while (iwcRef != null) {
            iwcRef = iwcRef.
        }

     */
    }
}
