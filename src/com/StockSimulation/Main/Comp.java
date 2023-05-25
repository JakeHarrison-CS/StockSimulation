package com.StockSimulation.Main;
import java.util.ArrayList;
import java.util.Random;

import com.StockSimulation.Simulator.simulator;
import com.StockSimulation.Simulator.ChartDisplay;
public class Comp 
{
	public static void main(String[] args) 
	{
		System.out.println("Hello World");
		ArrayList<Double> appleData = new ArrayList<Double>();
		simulator apple = new simulator("Apple", "APPL", 11);
		System.out.println(apple); 	
		apple.updateTrend();
		System.out.println(apple);
		for(int i = 0;i<1000; i++) {
			apple.updateSeed();
			appleData.add(apple.getPrice());

		}
		ChartDisplay appleGraph = new ChartDisplay(appleData);
		System.out.println(apple);
	}
}
