package com.scienceminer.advent2024;


import com.scienceminer.utils.FileUtils;
import com.scienceminer.utils.NumCharUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day4 {

    private static String INPUTFILE = "/advent2024/inputDay4.txt";

    public static void main(String[] args) {

        ArrayList<String> arrList = FileUtils.readFileToArrayList(INPUTFILE);
        System.out.println(arrList.size());

        char[][] xmasGrid = parseInputToArray(arrList);

        System.out.println( xmasGrid[0].length );
       // System.out.println( xmasGrid[0].length );

        int val = processXmasGridPart2(xmasGrid);

        System.out.println(" total output : " + val );
    }


    public static char[][] parseInputToArray(ArrayList<String> arrList) {

        char[][] xmasGrid = new char[arrList.size()][arrList.get(0).length()];
        for (int i = 0; i < arrList.size(); i++) {
            String row = arrList.get(i);
            xmasGrid[i] = row.toCharArray();
        }

        for (int i = 0 ; i < xmasGrid.length; i++ ) {
            for (int j = 0; j < xmasGrid[i].length ; j++ )
                System.out.print( xmasGrid[i][j] + " ");
            System.out.println();
        }

        return xmasGrid;

    }

    public static int processXmasGrid(char[][] grid) {
        int total = 0;
        int found = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                char c = grid[i][j];
                if (c == 'X' ) {
                    //System.out.println(" checking at " + i + " and " + j );
                    found = checkForPattern(grid, i, j);

                    total = found + total;
                }
            }

        }

        return total;
    }

    public static int processXmasGridPart2(char[][] grid) {
        int total = 0;
        int found = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                char c = grid[i][j];
                if (c == 'M' || c == 'S') {
                    //System.out.println(" checking at " + i + " and " + j );
                    found = checkForXmasPattern(grid, i, j);

                    total = found + total;
                }
            }

        }

        return total;
    }

    public static int checkForPattern(char[][] grid, int i , int j) {

        int total = 0;
        char[] pattern = {'X', 'M', 'A', 'S' };
        int val = check(Dir.DOWN,grid,i,j,pattern);
        int val1 = check(Dir.UP,grid,i,j,pattern);
        int val2 = check(Dir.RIGHT,grid,i,j,pattern);
        int val3 = check(Dir.LEFT,grid,i,j,pattern);
        int val4 = check(Dir.NE,grid,i,j,pattern);
        int val5 = check(Dir.NW,grid,i,j,pattern);
        int val6 = check(Dir.SE,grid,i,j,pattern);
        int val7 = check(Dir.SW,grid,i,j,pattern);

        total = val + val1 + val2 + val3 + val4 + val5 + val6 + val7;
        //System.out.println(" found " + total + " at " + i + " " + j);
        return total;
    }

    public static int checkForXmasPattern(char[][] grid, int i , int j) {

        int total = 0;
        char[] pattern1 = {'M', 'A', 'S' };
        char[] pattern2 = {'S', 'A', 'M' };
        int found = 0;
        if (j+2 < grid.length && i+2 < grid.length ) {
            int val1 = check(Dir.SE, grid, i, j, pattern1);
            int val2 = check(Dir.SE, grid, i, j, pattern2);
            int val3 = check(Dir.SW, grid, i,j + 2, pattern1);
            int val4 = check(Dir.SW, grid, i,j+2, pattern2);

            if (( val1 == 1 || val2 == 1) && ( (val3 == 1) || (val4 == 1) ) ) {
                found = 1;
            }
        }

        //int val2 = check(Dir.SE,grid,i,j,pattern2);
        //int val3 = check(Dir.SW,grid,i,j,pattern1);
        //int val4 = check(Dir.SW,grid,i,j,pattern2);

        total = found;
        //System.out.println(" found " + total + " at " + i + " " + j);
        return total;
    }

    public static int check ( Dir dir, char[][] grid, int i, int j , char[] inputPattern ) {
        // i j is a MATCH (X)
        int val = 0;
        int patternPos = 0;
        int limit = inputPattern.length;
        int matrixWidth = grid[i].length;

        if (dir == Dir.LEFT) { // this is left left
            // x coord remains constant.
            for (int l = j; l > j-limit; l-- ) {
                if ( l > -1 ) {
                    //System.out.print(grid[i][l]);
                    if (grid[i][l] == inputPattern[patternPos])
                        val++;


                    patternPos++;
                }
            }
            //System.out.println();
        }
        else if (dir == Dir.RIGHT) { // this is right
            // y coordinate remains the same
            for (int l = j; l < j+limit; l++ ) {
                if (l < matrixWidth ) {
                    if (grid[i][l] == inputPattern[patternPos])
                        val++;

                    patternPos++;
                }
            }
        }
        else if (dir == Dir.DOWN ) { // this is down
            for (int k = i; k < i+limit; k++ ) {
                if (k < matrixWidth ) {
                    if (grid[k][j] == inputPattern[patternPos])
                        val++;

                    patternPos++;
                }
            }
        }
        else if (dir == Dir.UP ) {
            for (int k = i; k > i-limit; k-- ) {
                if (k > -1 ) {
                    if (grid[k][j] == inputPattern[patternPos])
                        val++;

                    patternPos++;
                }
            }
        }
        else if (dir == Dir.SW ) {
            for(int k = i, l = j; k < i+limit; k++,l-- ){
                if ( k < matrixWidth  && l > -1 ) {
                    if (grid[k][l] == inputPattern[patternPos])
                        val++;

                    patternPos++;
                }
            }
        }
        else if (dir == Dir.SE ) {
            for(int k = i, l = j; k < i+limit; k++,l++ ){
                if (k < matrixWidth   && l < matrixWidth ) {
                    if (grid[k][l] == inputPattern[patternPos])
                        val++;

                    patternPos++;
                }
            }
        }
        else if (dir == Dir.NE ) {
            for(int k = i, l = j; k > i-limit; k--,l++ ){
                    if (k > -1  && l < matrixWidth ) {
                        if (grid[k][l] == inputPattern[patternPos])
                            val++;

                        patternPos++;
                    }
            }
        }
        else if (dir == Dir.NW ) {
            for(int k = i, l = j; k > i-limit; k--,l-- ){
                if ( k > -1  && l > -1 ) {
                    if (grid[k][l] == inputPattern[patternPos])
                        val++;

                    patternPos++;
                }
            }
        }

        if (val == inputPattern.length ) {
            System.out.println(" found XMAS " + dir.toString() + " at " + i + " " + j );
            return 1;
        }

        return 0;
    }

    public enum Dir {

        UP("UP"), DOWN("DOWN"), LEFT("LEFT"), RIGHT("RIGHT"),NE("NE"),SE("SE"),NW("NW"),SW("SW");

        private String dir;

        private Dir(String d) {
            this.dir = d;
        }
    }



}