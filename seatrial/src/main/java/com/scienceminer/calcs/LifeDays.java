package com.scienceminer.calcs;

//class	creation-forgot to call methods with constructor - cannot be referenced in a non-static ctxt

import java.util.Calendar;


public class LifeDays
{
	public LifeDays()
	{
	}

	public LifeDays(int myYear, int myDay, int myMonth )
	{

		initialise(myYear,myDay,myMonth);

	}

	private void initialise(int myYear, int myDay, int myMonth )
	{
		firstYear = myYear;
		firstDayOfMonth = myDay;
		firstMonth = myMonth;
		yearTotal = 0;
		total = 0;
	}
	
	public String isInterestingDate( int value ) {
		
		value = value+1;
		if ( (value % 50) == 0 ) 
			return " Value for tomorrow: " + value;
			
		return "";
	}

	public int calculate()
	{
		try
		{
			yearTotal = 0;
			total = 0;

			Calendar current = Calendar.getInstance();

			Calendar original = Calendar.getInstance(); 
			original.set(Calendar.MONTH,	firstMonth );
			original.set(Calendar.DAY_OF_MONTH,		firstDayOfMonth );
			original.set(Calendar.YEAR,	firstYear );

			Calendar lastDayinYear = Calendar.getInstance(); 
			lastDayinYear.set(Calendar.MONTH,	Calendar.DECEMBER);
			lastDayinYear.set(Calendar.DAY_OF_MONTH,		31 );

			int startYear = original.get(Calendar.YEAR);

			int currentYear = current.get(Calendar.YEAR);	

			while (startYear <= currentYear)
			{
				lastDayinYear.set(Calendar.YEAR, startYear);
				yearTotal = lastDayinYear.get(Calendar.DAY_OF_YEAR);				

				if ((startYear == firstYear) && (startYear != currentYear) )
				{
					total = yearTotal + total - original.get(Calendar.DAY_OF_YEAR);
				}
				else if (startYear < currentYear )
				{
					total = yearTotal + total;
				}
				else if (startYear == currentYear && startYear != firstYear )
				{
					total = total + current.get(Calendar.DAY_OF_YEAR);

				}
				else if (startYear == currentYear && startYear == firstYear )
				{
					total = total + current.get(Calendar.DAY_OF_YEAR) - original.get(Calendar.DAY_OF_YEAR) ;

				}

				startYear++;
			}

		}
		catch (Exception e)
		{
			System.out.println("NO class SUCCEEDED\n"  + e +"\n");
		}

		return total;

	}
	
	public int calculate(int inputDay, int inputMonth , int inputYear )
	{
		try
		{
			yearTotal = 0;
			total = 0;

			Calendar current = Calendar.getInstance();
			current.set(Calendar.MONTH,	 inputMonth );
			current.set(Calendar.DAY_OF_MONTH,		inputDay );
			current.set(Calendar.YEAR,	inputYear );

			Calendar original = Calendar.getInstance(); 
			original.set(Calendar.MONTH,	firstMonth );
			original.set(Calendar.DAY_OF_MONTH,		firstDayOfMonth );
			original.set(Calendar.YEAR,	firstYear );

			Calendar lastDayinYear = Calendar.getInstance(); 
			lastDayinYear.set(Calendar.MONTH,	Calendar.DECEMBER);
			lastDayinYear.set(Calendar.DAY_OF_MONTH,		31 );

			int startYear = original.get(Calendar.YEAR);

			int currentYear = current.get(Calendar.YEAR);	

			while (startYear <= currentYear)
			{
				lastDayinYear.set(Calendar.YEAR, startYear);
				yearTotal = lastDayinYear.get(Calendar.DAY_OF_YEAR);				

				if ((startYear == firstYear) && (startYear != currentYear) )
				{
					total = yearTotal + total - original.get(Calendar.DAY_OF_YEAR);
				}
				else if (startYear < currentYear )
				{
					total = yearTotal + total;
				}
				else if (startYear == currentYear && startYear != firstYear )
				{
					total = total + current.get(Calendar.DAY_OF_YEAR);

				}
				else if (startYear == currentYear && startYear == firstYear )
				{
					total = total + current.get(Calendar.DAY_OF_YEAR) - original.get(Calendar.DAY_OF_YEAR) ;

				}

				startYear++;
			}

		}
		catch (Exception e)
		{
			System.out.println("NO class SUCCEEDED\n"  + e +"\n");
		}

		return total;

	}

	

	int firstYear;
	int firstDayOfMonth;
	int firstMonth;
	int yearTotal;
	int total;

   public String checkForInterestingDates() {

	   String interesting = new String();
	   String overall = new String();
	   
		LifeDays ld1 = new LifeDays(1971,30,Calendar.APRIL);
		int result = ld1.calculate();
		interesting = ld1.isInterestingDate(result);
		
		if (interesting.length() > 0 )
			overall = overall + "\n ERC:  " + interesting;
		
		ld1 = new LifeDays(1972,19,Calendar.JANUARY);
		result = ld1.calculate();
		System.out.println("DJN Result:\t" + result);

		interesting = ld1.isInterestingDate(result);
		
		if (interesting.length() > 0 )
			overall = overall + "\n DJN: " + interesting;
		
		ld1 = new LifeDays(2003,20,Calendar.APRIL);
		result = ld1.calculate();
		System.out.println("ORC Result:\t" + result);
		interesting = ld1.isInterestingDate(result);
		
		if (interesting.length() > 0 )
			overall = overall + "\n ORC: " + interesting;
		
		ld1 = new LifeDays(2005,15,Calendar.JULY);
		result = ld1.calculate();
		System.out.println("SIC Result:\t" + result);
		interesting = ld1.isInterestingDate(result);
		
		if (interesting.length() > 0 )
			overall = overall + "\n SIC: " + interesting;
		
		ld1 = new LifeDays(2010,12,Calendar.JULY);
		result = ld1.calculate();
		System.out.println("CSC Result:\t" + result);

		interesting = ld1.isInterestingDate(result);
		
		if (interesting.length() > 0 )
			overall = overall + "\n CSC: " + interesting;
		
		ld1 = new LifeDays(1930,14,Calendar.AUGUST);
		result = ld1.calculate();
		System.out.println("GRC Result:\t" + result);

		interesting = ld1.isInterestingDate(result);
		
		if (interesting.length() > 0 )
			overall = overall + "\n GRC: " + interesting;
		
		ld1 = new LifeDays(1932,25,Calendar.OCTOBER);
		result = ld1.calculate();
		System.out.println("BEC Result:\t" + result);

		interesting = ld1.isInterestingDate(result);
		
		if (interesting.length() > 0 )
			overall = overall + "\n BEC: " + interesting;
		
		ld1 = new LifeDays(1930,14,Calendar.AUGUST);
		result = ld1.calculate(10, Calendar.MARCH, 2022);
		System.out.println("GRC Final Result:\t" + result);

		interesting = ld1.isInterestingDate(result);
		
		if (interesting.length() > 0 )
			overall = overall + "\n GRC Final : " + interesting;
		
		if ( overall.length() >0 )
			return overall;
		
		return null;
		
   }

	public static void main(String[] args) 
	{

		LifeDays ld1 = new LifeDays(1971,30,Calendar.APRIL);
		int result = ld1.calculate();
		String interesting = ld1.isInterestingDate(result);
		System.out.println("ERC Result:\t" + result);

		ld1 = new LifeDays(1972,19,Calendar.JANUARY);
		result = ld1.calculate();
		System.out.println("DJN Result:\t" + result);

		ld1 = new LifeDays(2003,20,Calendar.APRIL);
		result = ld1.calculate();
		System.out.println("ORC Result:\t" + result);

		ld1 = new LifeDays(2005,15,Calendar.JULY);
		result = ld1.calculate();
		System.out.println("SIC Result:\t" + result);

		ld1 = new LifeDays(2010,12,Calendar.JULY);
		result = ld1.calculate();
		System.out.println("CSC Result:\t" + result);

		ld1 = new LifeDays(1932,25,Calendar.OCTOBER);
		result = ld1.calculate();
		System.out.println("BEC Result:\t" + result);

		ld1 = new LifeDays(1930,14,Calendar.AUGUST);
		result = ld1.calculate(10, Calendar.MARCH, 2022);
		System.out.println("GRC Final Result:\t" + result);

	}


}
