package com.StockSimulation.Simulator;

import java.util.Random;

public class simulator {
	private String name; // Name of company, etc Apple
	private String symbol; // Symbol of stock, etc APPL
	private double seed; // the amount of fluctuation of the stock, 0-1 (the lower the less random)
	private double trend; // Whether it will tend to trend up or down, -1 - 1 (below 0 is down above is up)
	private double initial;
	Random rand = new Random();
	public simulator(String name, String symbol, double seed, double trend, double initial) 
	{
		this.name = name;
		this.symbol = symbol;
		this.seed = seed;
		this.trend = trend;
		this.initial = initial;
				
	}
	
	public simulator(String name, String symbol, double initial) 
	{
		
		this.name = name;
		this.symbol = symbol;
		this.initial = initial;
		seed = rand.nextDouble(1);

				
	}

	public void updateTrend()
	{
		trend = rand.nextDouble(-1, 1);
	}
	
	public void updateSeed() 
	{
		if (trend > 0) {
			
		}
	}
	/**
	 * @return the trend
	 */
	public double getTrend() {
		return trend;
	}

	/**
	 * @param trend the trend to set
	 */
	public void setTrend(double trend) {
		this.trend = trend;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
		
	public double getSeed() {
		return seed;
	}
	
	public double bias(int direction, double number, double bias)
	{
		if(direction > 0)
		{
			double biasFactor = rand.nextInt(0, 100); // choose a random number between 0-100 to calculate the bias
			if(biasFactor > bias) // If the number is less than 80 (20% chance) no bias
			{
				number += rand.nextDouble(-1, 1);
			}else 
			{
				number += rand.nextDouble(-1,0.1); // Greater chance of going down
			}
		}	
		else 
		{
	

			double biasFactor = rand.nextInt(0, 100); // choose a random number between 0-100 to calculate the bias
			if(biasFactor > bias) // If the number is less than 80 (20% chance) no bias
			{
				number = rand.nextDouble(-1, 1);
			}
			else 
			{
				number = rand.nextDouble(-0.1,1); // Greater chance of going up
			}
		}
		return number;
		
	}
	
}
