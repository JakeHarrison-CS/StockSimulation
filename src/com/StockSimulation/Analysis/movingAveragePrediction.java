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
    public double movingAverageRun() {
        boolean bought = false;
        for (int i = 0; i < stockPrice.size(); i++) {
            if (stockPrice.get(i) > movingAverage.get(i) && movingAverage.get(i) != -1 && bought == false && money >= stockPrice.get(i)) {
                while ((money - stockPrice.get(i)) > 0) {
                    money = money - stockPrice.get(i);
                    shares += 1;
                }
                System.out.println("BOUGHT :" + money + " SHARES: " + shares + " STOCK PRICE: " + stockPrice.get(i));
                bought = true;
                //System.out.println("BOUGHT STOCK! " + "NEW MONEY: " + money + " STOCK PRICE: " + stockPrice.get(i) + " MOVING AVERAGE: " + movingAverage.get(i));

            }
            if (stockPrice.get(i) < movingAverage.get(i) && movingAverage.get(i) != -1 && bought == true && money + stockPrice.get(i) > 0) {
                for (int j = shares; j > 0; j--) {
                    money += stockPrice.get(i);
                    shares = j;
                }
                System.out.println("SOLD :" + money + " SHARES: " + shares + " STOCK PRICE: " + stockPrice.get(i));
                shares = 0;
                bought = false;


                //System.out.println("SOLD STOCK! " + "NEW MONEY: " + money + " STOCK PRICE: " + stockPrice.get(i) + " MOVING AVERAGE: " + movingAverage.get(i));
                //  System.out.println(" Money: " + money + " StartMoney: "+ startMoney + " Money - Startmoney:  " + (money-startMoney));
            }
            if (shares > 0 && i == stockPrice.size() - 1)
            {
                    for (int j = 0; j < shares; j++)
                    {
                        money += stockPrice.get(i);
                    }
                System.out.println("SOLD FINAL :" + money + " SHARES: " + shares + " STOCK PRICE: " + stockPrice.get(i));
                shares = 0;
                    bought = false;


            }

        }

            return money - startMoney;
    }

}
