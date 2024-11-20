package com.scienceminer.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileUtils {

    public static ArrayList<String> readFileToArrayList(String filePath) {
        ArrayList<String> arrList = new ArrayList<>();

        try (InputStream inputStream = com.scienceminer.directoryparser.DirSizeParser.class.getResourceAsStream(filePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                arrList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrList;
    }


}
