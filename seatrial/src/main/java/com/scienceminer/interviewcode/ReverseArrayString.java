package com.scienceminer.interviewcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ReverseArrayString {

	
	public static void main(String[] args) {
		
//		1. read in input line into array String
//		2. reverse this array and display
		
		Scanner scan = new Scanner(System.in);

	      System.out.println("Please enter data separated by spaces: ");
	      String data = scan.nextLine();

	      String tmpDataArray[] = data.split(" ");

	      // int arrSize = stringArray.size() ;
	      int arrSize = tmpDataArray.length ;
		   
	      int arrLocation = arrSize-1;
	      for (arrLocation = arrSize-1; arrLocation >= 0 ; arrLocation = arrLocation-1 )
	      {
	    	 // System.out.print( stringArray[arrSize]);
	    	  System.out.print(  tmpDataArray[arrLocation] + "  ");
	      }
	      
	      System.out.println();
	      
	      // Now using an arrayList
	      ArrayList<String> stringArray = new ArrayList<String>(Arrays.asList(data.split(" ")));
	      
	     int stringArraySize =  stringArray.size();
	     
	     for ( int pos = stringArraySize-1 ; pos >= 0 ; pos = pos - 1)  {
	    	 System.out.print(  " " + stringArray.get(pos));
	     }
	     
	     System.out.println();
	    
		
	}
}
