package com.scienceminer.interviewcode;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Powerset implements Cloneable {

	final static Logger logger = LogManager.getLogger("Powerset");

	public ArrayList<ArrayList<Integer>> getPowerset() {
		return powerset;
	}

	private ArrayList<ArrayList<Integer>> powerset;
	private ArrayList<Integer> inputSet;

	public static void  main(String[] args )
	{

		Powerset ps = new Powerset(8);		
		Powerset result;
		try {
			result = ps.calculate();
			
			logger.info( " RESULT: " + result.powerset.toString() );
			logger.info(" Number of items: " + result.powerset.size() );

		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	public Powerset( )
	{
		
		inputSet = new ArrayList<Integer>();
		powerset = new  ArrayList<ArrayList<Integer>>();

	}

	public Powerset( Integer input )
	{		

		inputSet = new ArrayList<Integer>();

		for (int i = 1; i <= input ; i++ ) {
			inputSet.add(i);
		} 

		powerset = new  ArrayList<ArrayList<Integer>>();

	}

	
	public Powerset( Powerset ps )
	{
		this.powerset = new ArrayList<ArrayList<Integer>>();

		if  (ps != null ) {

			for ( ArrayList<Integer> singleArray : ps.powerset )
			{
				ArrayList<Integer> newSingleArray = new ArrayList<Integer> ();
				newSingleArray.addAll(singleArray);
				
				this.powerset.add(newSingleArray);
			}

		}
		

	}

	 
	 @Override
	 protected Object clone() throws CloneNotSupportedException {
		 
		 Powerset pow = (Powerset) super.clone();

		 pow.powerset = new ArrayList<ArrayList<Integer>>();

		 for ( ArrayList<Integer> singleArray : this.powerset )
		 {
			 ArrayList<Integer> newSingleArray = new ArrayList<Integer> ();
			 newSingleArray.addAll(singleArray);

			 pow.powerset.add(newSingleArray);
		 }

		 return pow;
	 }


	public Powerset calculate() throws CloneNotSupportedException {

		logger.debug(" POWERSET: " + inputSet.toString() );

		if ( inputSet.size() > 0 ) {

			Integer first = inputSet.remove(0); // firstItem 
			
			Powerset powersetInterim = calculate(); // so does power
			
			//Powerset powerset2 = new Powerset(powersetInterim); // deep copy
			Powerset powerset2 = (Powerset) powersetInterim.clone(); // deep copy via clone
			
			Powerset addedToAll = addToAll(first,powerset2);
			powerset.addAll(addedToAll.powerset);

			return this;
		}
		else { 
			
			logger.debug(" powersetC, NEW POWERSET " );
			return new Powerset();
			
		}

	}
	

	public ArrayList<ArrayList<Integer>> deepCopy( ArrayList<ArrayList<Integer>> inputArray ) {

		ArrayList<ArrayList<Integer>> inputClone = new ArrayList<ArrayList<Integer>>();

		for (ArrayList<Integer> singleArray : inputArray )
		{
			ArrayList<Integer> newSingleArray = new ArrayList<Integer> ();

			newSingleArray.addAll(singleArray);
			inputClone.add(newSingleArray);
		}

		return inputClone;
	}



	public ArrayList<ArrayList<Integer>> addToAll(Integer first,  ArrayList<ArrayList<Integer>> tails  ) {

		logger.debug( ">>>>>>>  addToAll: " + first + " to " + tails.toString() );

		for (ArrayList<Integer> inArr : tails ) {
			inArr.add(first);
		}

		// Add the single Integer as its own entry - FATAIL initial mistake was to leave this out - OUCH!!
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		intArray.add(first);
		tails.add(intArray);

		return tails;

	}



	public Powerset addToAll(Integer first,  Powerset tails  ) {

		logger.debug( ">>>>>>>  addToAll: " + first + " to " + tails.toString() );

		for (ArrayList<Integer> inArr : tails.powerset ) {
			inArr.add(first);
		}

		// Add the single Integer as its own entry - FATAIL initial mistake was to leave this out - OUCH!!
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		intArray.add(first);
		tails.powerset.add(intArray);

		return tails;

	}

}
