package com.StockSimulation.Main;
import java.util.Random;

import com.StockSimulation.Simulator.simulator;
public class Comp 
{
	public static void main(String[] args) 
	{
		System.out.println("Hello World");
		simulator apple = new simulator("Apple", "APPL", 11);
		System.out.println(apple); 	
		apple.updateTrend();
		System.out.println(apple);
		for(int i = 0;i<1000; i++) {
			apple.updateSeed();
			System.out.println(apple.getPrice());
		}
		System.out.println(apple);
	}
}
