package com.scienceminer.interviewcode;

public class FizzBuzz {

	boolean Condition(int i, int divider)
	{
		return (i % divider == 0);
	}
	
	boolean Condition(int i, int divider, String output)
	{
		if (i % divider == 0)
		{
			System.out.print(output);
			return true;
		}
		
		return false;
	}
	
	
	static void process(int lower, int upper)
	{
		FizzBuzz fb = new FizzBuzz();

		for (int i = lower; i <= upper; i++)
		{
			boolean fizzy = fb.Condition(i,3,"Fizz");
			boolean buzzy = fb.Condition(i,5,"Buzz");

			if (!fizzy & !buzzy)
				System.out.print(i);

			System.out.println();
		}

	}
	
	public  static void main(String[] args )
	{
		FizzBuzz.process(1,200);
	}
	
}
