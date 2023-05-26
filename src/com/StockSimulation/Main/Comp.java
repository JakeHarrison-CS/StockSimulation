package com.StockSimulation.Main;
import java.util.ArrayList;
import com.StockSimulation.Simulator.simulator;
import com.StockSimulation.Simulator.ChartDisplay;
import java.util.Random;
import java.util.concurrent.TimeUnit;
public class Comp
{
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello World");
		ChartDisplay appleGraph = new ChartDisplay("Apple Stock", "Time (Seconds)", "Price (USD)");

		ArrayList<Double> appleData = new ArrayList<Double>();
		simulator apple = new simulator("Apple", "APPL", 100);
		System.out.println(apple); 	
		apple.updateTrend();
		System.out.println(apple);
		for(int i = 0;i<1000; i++) {
			apple.updateSeed();
			appleData.add(apple.getPrice());
			//TimeUnit.MILLISECONDS.sleep(500);


		}
		appleGraph.updateData(appleData);
		System.out.println(apple);
	}
}
