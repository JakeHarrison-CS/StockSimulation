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

	/**
	 * A more expanded version to create a stock (used for debugging/testing)
	 * @param name The name of the Stock
	 * @param symbol The symbol of the stock
	 * @param seed The initial seed of the stock
	 * @param trend The initial trend of the stock
	 * @param price The initial price of the stock
	 */
	public simulator(String name, String symbol, double seed, double trend, double price)
	{
		this.name = name;
		this.symbol = symbol;
		this.seed = seed;
		this.trend = trend;
		this.price = price;

	}

	/**
	 * Creates the stock to simulate (use by default)
	 * @param name The name of the stock
	 * @param symbol The symbol of the stock
	 * @param price The initial price of the stock
	 */
	public simulator(String name, String symbol, double price)
	{

		this.name = name;
		this.symbol = symbol;
		this.price = price;
		seed = 0;

	}

	/**
	 * This method generates a new trend when called
	 */
	public void updateTrend()
	{
		trend = rand.nextGaussian(0,1); // generates a random guassian (meaning more stable and predictable movement vs a random double)
		trendPrice = price + (price*trend); // Sets the goal price till new trend is set
		if (trendPrice < 0)
		{
			updateTrend(); // A stock can't be worth less than 0, so we regenerate till its greater than
		}
	}

	/**
	 * This method updates the seed/price of the stock. It generates a random number with bias towards the direction of the stock then adds it to the price.
	 */
	public void updateSeed() {
		seed = rand.nextDouble(-1, 1); // Generate a random number between -1 and 1

		if ((price + seed) < 0) {
			price = 0;
		} else {
			price += seed;
		}

		// Check if the stock is trending downwards
		if (trend < 0) {
			// Check if the price has reached the trend position
			if (price <= trendPrice) {
				updateTrend(); // Generate a new trend
				System.out.println("-----------------" + trendPrice + "-----------------");
			}
		} else {
			// Check if the stock is trending upwards
			if (price >= trendPrice) {
				updateTrend(); // Generate a new trend
				System.out.println("-----------------" + trendPrice + "-----------------");
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

	/**
	 * This method generates a bias number either upwards or downwards. The direction determines if it has upwards or downwards bias.
	 * @param direction If the direction is above 0 it will have upward bias, if direction is below zero it will have downward bias
	 * @param bias The percentage of chance for bias
	 * @return returns the number generated
	 */
	private double bias(int direction, double bias)
	{
		double number;
		double biasFactor;
		if(direction < 0)
		{
			biasFactor = rand.nextInt(0, 100); // choose a random number between 0-100 to calculate the bias
			if(biasFactor > bias) // If the number is less bias amount no bias
			{
				number = rand.nextDouble(-1, 1);
			}else
			{
				number = rand.nextDouble(-1,0.3); // Greater chance of going down
			}
		}
		else
		{
			biasFactor = rand.nextInt(0, 100); // choose a random number between 0-100 to calculate the bias
			if(biasFactor > bias) // If the number is less  bias amount no bias
			{
				number = rand.nextDouble(-1, 1);
			}
			else
			{
				number = rand.nextDouble(-0.3,1); // Greater chance of going up
			}
		}

		return number;


	}

	/**
	 * Provides all the info in one method
	 * @return All the information including: name, symbol, price, trend
	 */
	public String toString()
	{
		return "Company Name: " + name + "\n" + "Ticker Symbol: " + symbol + "\n" + "Current Price: " + price + "\n" + "Trend Price: " + trendPrice;
	}
}
