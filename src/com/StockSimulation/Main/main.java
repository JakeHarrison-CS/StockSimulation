package com.StockSimulation.Main;
import java.util.Random;

import com.StockSimulation.Simulator.simulator;
public class main {
	public static void main(String[] args) {
		System.out.println("Hello World");
		simulator apple = new simulator("Apple", "APPL");
		System.out.println(apple.getSeed());
		Random rand = new Random();
		for(int i = 0; i<20000000; i++) {
			double seed = rand.nextDouble();
			if(seed == 0.7495285385623206)

			{
				System.out.println("YEAHHHH");
				System.exit(0);
			}
			System.out.println(seed);
		}
	}
}
