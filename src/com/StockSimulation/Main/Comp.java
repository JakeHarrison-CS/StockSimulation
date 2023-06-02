package com.StockSimulation.Main;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.StockSimulation.Simulator.Stock;
import com.StockSimulation.Analysis.movingAveragePrediction;
public class Comp
{
	public static void main(String[] args) throws IOException, InterruptedException {
        Stock apple = new Stock("Apple", "AAPL",2);
        List applePrice = apple.getPrice();
        List appleMovingAverage = apple.getMovingAverage();
        apple.makeGraph();
        System.out.println(Arrays.toString(applePrice.toArray()));
        System.out.println(Arrays.toString(appleMovingAverage.toArray()));
        movingAveragePrediction appleMA = new movingAveragePrediction(apple, 1000 );
        int highestMATerm = 0;
        int lowestMATerm = 0;
        double highestMA = appleMA.movingAverageRun();
        double lowestMA = appleMA.movingAverageRun();
        for(int i =1; i <= 500; i++){
                apple.setMovingAverageTerm(i);
                System.out.println("Profit ("+i+"): " + appleMA.movingAverageRun());
                if(appleMA.movingAverageRun() >highestMA)
                {
                        highestMA = appleMA.movingAverageRun();
                        highestMATerm = i;
                }
                if(appleMA.movingAverageRun() <lowestMA)
                {
                            lowestMA = appleMA.movingAverageRun();
                            lowestMATerm = i;
                }
        }
        System.out.println("Highest Profit: " + highestMA);
        System.out.println("Highest Term: " + highestMATerm);
        System.out.println("Lowest Profit: " + lowestMA);
        System.out.println("Lowest Term: " + lowestMATerm);



    }

}
