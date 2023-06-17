package com.StockSimulation.Analysis;
import com.StockSimulation.Simulator.Stock;
import java.util.ArrayList;

public class movingAveragePrediction{
    private int shares = 0;
    public double money;
    public double startMoney;
    private ArrayList<Double> stockPrice = new ArrayList<Double>();
    private ArrayList<Double> movingAverage = new ArrayList<Double>();
    public movingAveragePrediction(Stock stock, double money){
        this.stockPrice = stock.getPrice();
        this.startMoney = money;
        this.money = money;

        this.movingAverage = stock.getMovingAverage();
    }
    /**
     * Runs the moving average prediction
     * @return (double) the profit
     */
    public double movingAverageRun() {
        boolean bought = false; // If the bot has recently bought the stock
        for (int i = 0; i < stockPrice.size(); i++) { // Loop through the stock price
            // Checks if the stock price is greater than the moving average and if the bot has not bought the stock yet
            if (stockPrice.get(i) > movingAverage.get(i) && movingAverage.get(i) != -1 && bought == false && money >= stockPrice.get(i)) {
                // If so, buy the stock until the bot runs out of money
                while ((money - stockPrice.get(i)) > 0) {
                    money = money - stockPrice.get(i);
                    shares += 1;
                }
                bought = true;
            }
            // Checks if the stock price is less than the moving average and if the bot has bought the stock
            if (stockPrice.get(i) < movingAverage.get(i) && movingAverage.get(i) != -1 && bought == true && money + stockPrice.get(i) > 0) {
                // If so, sell the stock until the bot runs out of shares
                for (int j = shares; j > 0; j--) {
                    money += stockPrice.get(i);
                    shares = j;
                }
                // Set bought to false and shares to 0 to indicate that the bot has sold all of its shares
                shares = 0;
                bought = false;
            }
            // Checks if the bot has shares left and if the stock price is the last stock price
            if (shares > 0 && i == stockPrice.size() - 1)
            {
                // Sell all of the shares
                for (int j = 0; j < shares; j++)
                {
                    money += stockPrice.get(i);
                }
                shares = 0;
                bought = false;


            }

        }

        // Return the profit
            return money - startMoney;
    }

}
