package com.StockSimulation.Simulator;

import java.util.Random;

public class simulator {
	private String name; // Name of company, etc Apple
	private String symbol; // Symbol of stock, etc APPL
	private double seed; // the amount of fluctuation of the stock, 0-1 (the lower the less random)
	private double trend; // Whether it will tend to trend up or down, -1 - 1 (below 0 is down above is up)
	private double price;
	private double trendPrice;
	Random rand = new Random();
	public simulator(String name, String symbol, double seed, double trend, double price) 
	{
		this.name = name;
		this.symbol = symbol;
		this.seed = seed;
		this.trend = trend;
		this.price = price;
				
	}
	
	public simulator(String name, String symbol, double price) 
	{
		
		this.name = name;
		this.symbol = symbol;
		this.price = price;
		seed = 0;

				
	}

	public void updateTrend()
	{
		trend = rand.nextDouble(-0.99, 1);
		trendPrice = price + (price*trend); // Sets the goal price till new trend is set
		if (trendPrice < 0)
		{
			updateTrend(); // A stock can't be worth less than 0 so we regenerate till its greater than
		}
	}
	
	public void updateSeed() 
	{
		if(trend < 0)
		{
			if(price <= trendPrice) 
			{
				updateTrend();
				System.out.println("-----------------" + trendPrice + "-----------------");// When the trend price reaches the actual price we want to add to the price
			}
		}
		else {
			if(price >= trendPrice) 
			{
				updateTrend();
				System.out.println("-----------------" + trendPrice + "-----------------");// When the trend price reaches the actual price we want to add to the price
			}
		}
		
		if (trend > 0) 
		{ 
			seed = bias(1, 65);
			if((price + seed) < 0)
			{
				price = 0;
			}
			else 
			{
				price += seed;
			}
		}
		if (trend < 0) 
		{
			seed = bias(-1, 65);
			if((price + seed) < 0)
			{
				price = 0;

			}
			else 
			{
				price += seed;
			}

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
	
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	private double bias(int direction, double bias)
	{
		double number;
		double biasFactor;
		if(direction < 0)
		{
			biasFactor = rand.nextInt(0, 100); // choose a random number between 0-100 to calculate the bias
			if(biasFactor > bias) // If the number is less bias amount no bias
			{
				number = rand.nextDouble(-3, 3);
			}else 
			{
				number = rand.nextDouble(-3,0.3); // Greater chance of going down
			}
		}	
		else 
		{
	

			biasFactor = rand.nextInt(0, 100); // choose a random number between 0-100 to calculate the bias
			if(biasFactor > bias) // If the number is less  bias amount no bias
			{
				number = rand.nextDouble(-3, 3);
				
			}
			else 
			{
				number = rand.nextDouble(1,3); // Greater chance of going up
			}
		}

		return number;
		
		
	}

	public String toString()
	{
		return "Company Name: " + name + "\n" + "Ticker Symbol: " + symbol + "\n" + "Current Price: " + price + "\n" + "Trend Price: " + trendPrice;
	}
}
