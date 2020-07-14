package com.scienceminer.interviewcode;

//import StdRandom;

/**
 * @author scienceMiner
 * Monte Carlo PI generation
 * This provides an approximation to PI based on
 * hits on a square enclosing the first 90 degrees
 * of a circle.
 */

public class MonteCarloPI {
	int _throws;

	int _sampleWidth;

	public MonteCarloPI(int trials, int width) {
		_throws = trials;
		_sampleWidth = width;

	}

	/**
	 * @return
	 */
	double calculatePI() {

		int hits = 0;

		for (int i = 0; i < _throws; i++) {
			double x = StdRandom.uniform(0, _sampleWidth);
			double y = StdRandom.uniform(0, _sampleWidth);

			double position = Math.sqrt((x * x) + (y * y));

			if (position <= _sampleWidth) {
				hits++;
			}
		}

		System.out.println("hits:" + hits);

		double num = new Double(hits) / new Double(_throws);

		return 4 * num;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// set to 1 * 10^8 seems to guarantee correctness to only 4 d.p.
		MonteCarloPI m = new MonteCarloPI(100000000, 10);

		double pi = m.calculatePI();

		System.out.println("PI:" + pi);

	}

};
