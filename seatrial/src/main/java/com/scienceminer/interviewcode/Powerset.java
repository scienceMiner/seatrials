package com.scienceminer.interviewcode;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class Powerset {

	final static Logger logger = Logger.getLogger(Powerset.class);

	public static void  main(String[] args )
	{

		Powerset ps = new Powerset();
		ArrayList<Integer> inputSetArray = new ArrayList<Integer>();
		
		Integer setLimit = 8;
		
		for (int i = 1; i <= setLimit ; i++ ) {
			inputSetArray.add(i);
		}
		
		ArrayList<ArrayList<Integer>> result = ps.powerset(inputSetArray);
		
		logger.info( " RESULT: " + result.toString() );
		logger.info(" Number of items: " + result.size() );
		
	}


	//	Input : [1,2,3]
	//			add 1 to all powersets of [2,3]	+ all powersets of [2,3] - result [[1,2],[1,2,3],[1],[1,3],  [2],[2,3],[],[3]]
	//					add 2 to all powersrs of [3] + all powersets of [3]		- result [[2],[2,3],[],[3]]
	//																			add 3 to all powersets of [] + all powerset of [] - result [[],[3]]


	public ArrayList<ArrayList<Integer>> powerset(ArrayList<Integer> inputSet) {

		logger.debug(" POWERSET: " + inputSet.toString() );
		
		if (inputSet.size() > 0 ) {
			
			Integer first = inputSet.remove(0); // firstItem 
			
			ArrayList<ArrayList<Integer>> powerset = powerset(inputSet);
			logger.debug(" ABOUT to deep Copy powerset:  " + powerset.toString() );
			
			ArrayList<ArrayList<Integer>> powerset2 = deepCopy(powerset);
			logger.debug(" cloned powerset: " + powerset2.toString() );
			
			ArrayList<ArrayList<Integer>> addedToAll = addToAll(first,powerset2);

			powerset.addAll(addedToAll);
		
			return powerset;
		}
		else { // if (inputSet.size() == 0 ) {
			return new ArrayList<ArrayList<Integer>>();
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

	private Integer[][] append(Integer[][] addedToAll, Integer[][] powerset) {
		// TODO Auto-generated method stub
		return null;
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
	

}
