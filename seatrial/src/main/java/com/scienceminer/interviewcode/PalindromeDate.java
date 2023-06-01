package com.scienceminer.interviewcode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.IntStream;

public class PalindromeDate {

		private static final SimpleDateFormat fdf = new SimpleDateFormat("HH:mm dd/MM yyyy");
		
		public static void main(String[] args) {
			
			IntStream.range(0,24).forEach ( HH ->
				IntStream.range(0,60).forEach ( mm -> 
					IntStream.range(0,31).forEach ( dd -> 
						displayIfPalindrome(HH,mm,dd) )));

		}
		
		public static String zeroPrint(Integer i ) {
			if (i < 10)
				return new String("0" + i.toString());
			else
				return i.toString();
		}
		
		public static Boolean validMonth( String in) {
			Integer mon = new Integer(in);
			if (mon > 1 && mon < 13) 
				return true;
			
			return false;
		}
		
		public static String reverseIt(String in ) {
			StringBuilder in1 = new StringBuilder();
			in1.append(in);
			
			return in1.reverse().toString();
		}
		
		public static void displayIfPalindrome( Integer HH, Integer mm, Integer dd) 
		{
			
			String firstHalf = zeroPrint(HH) + zeroPrint(mm) + zeroPrint(dd);
			
			StringBuilder input1 = new StringBuilder();
			input1.append(firstHalf);
			
			if (input1.equals(input1.reverse())) {
				String reverseddd = reverseIt(zeroPrint(dd));
				String reversedmm = reverseIt(zeroPrint(mm));
				String reversedhh = reverseIt(zeroPrint(HH));
				
				String validDate = zeroPrint(HH) + ":" + zeroPrint(mm) + " " + zeroPrint(dd) + "/" + reverseddd + " " + reversedmm + reversedhh;
				
				if (validMonth(reverseddd) && reversedmm.equals("20"))
				{
					try {
						
						System.out.println(" INPUT : " + validDate );		
						System.out.println(" palindrome date: " + fdf.parse(validDate).toString());
						
					}
					catch (ParseException e) {
						System.out.println(" EXCEPTION" + e.getLocalizedMessage() );
					}
					catch (IllegalArgumentException e) {
						System.out.println(" ILLEGAL ARG EXCEPTION" + e.getLocalizedMessage() );
						
					}
				}
			}
		}
}
