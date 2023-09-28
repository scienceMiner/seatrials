package com.scienceminer.chatgpt;

import java.util.Random;

public class AsianOptionPricer {
    
    public static double priceAsianCall(double spotPrice, double strikePrice, double riskFreeRate, 
            double volatility, double timeToMaturity, int numSimulations, int numTimeSteps) {
        
        double dt = timeToMaturity / numTimeSteps;
        double drift = riskFreeRate - 0.5 * volatility * volatility;
        double totalPayoff = 0;
        Random rand = new Random();
        
        for (int i = 0; i < numSimulations; i++) {
            double[] stockPrices = new double[numTimeSteps+1];
            stockPrices[0] = spotPrice;
            double sumStockPrices = spotPrice;
            
            for (int j = 1; j <= numTimeSteps; j++) {
                double epsilon = rand.nextGaussian();
                double stockPrice = stockPrices[j-1] * Math.exp(drift * dt + volatility * epsilon * Math.sqrt(dt));
                stockPrices[j] = stockPrice;
                sumStockPrices += stockPrice;
            }
            
            double averageStockPrice = sumStockPrices / (numTimeSteps+1);
            double payoff = Math.max(averageStockPrice - strikePrice, 0);
            totalPayoff += payoff;
        }
        
        double expectedPayoff = totalPayoff / numSimulations;
        double discountedPayoff = expectedPayoff * Math.exp(-riskFreeRate * timeToMaturity);
        return discountedPayoff;
    }
    
    public static void main(String[] args) {
        double spotPrice = 100;
        double strikePrice = 110;
        double riskFreeRate = 0.02;
        double volatility = 0.2;
        double timeToMaturity = 1.0;
        int numSimulations = 1000000;
        int numTimeSteps = 252;
        
        double optionPrice = priceAsianCall(spotPrice, strikePrice, riskFreeRate, volatility, 
                timeToMaturity, numSimulations, numTimeSteps);
        
        System.out.println("Asian call option price: " + optionPrice);
    }

}
