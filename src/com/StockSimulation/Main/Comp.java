package com.StockSimulation.Main;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.StockSimulation.Simulator.Stock;

public class Comp
{
	public static void main(String[] args) throws IOException, InterruptedException {
        Stock apple = new Stock("Tesla", "AAPL", 200);
        List applePrice = apple.getPrice();
        List appleMA = apple.getMovingAverage();
        apple.makeGraph();
        System.out.println(Arrays.toString(applePrice.toArray()));
        System.out.println(Arrays.toString(appleMA.toArray()));

    }

}
