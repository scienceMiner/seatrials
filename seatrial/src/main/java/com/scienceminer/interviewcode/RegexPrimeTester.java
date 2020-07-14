package com.scienceminer.interviewcode;

public class RegexPrimeTester { 
	
	  public static void main(String[] args) {
		 
	    System.out.println(String.format("%0" + args[0] + "d", 0).matches("^0$|^(00+?)\\1+$") ? "Not prime" : "Prime"); 
	  } 



	} 