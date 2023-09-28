package com.scienceminer.interviewcode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadInReadOut {

    public static void main ( String[] args ) throws IOException  {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
       // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String[] aTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> a = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aTemp[i]);
            a.add(aItem);
        }

        for (Integer i : a ) {
            System.out.printf("%d  ",i);
        }
    }
}
