package com.StockSimulation.Analysis;
import com.StockSimulation.Simulator.Stock;
import java.util.ArrayList;

public class movingAveragePrediction{
    private int shares = 0;
    public double money;
    private ArrayList<Double> stockPrice = new ArrayList<Double>();
    private ArrayList<Double> movingAverage = new ArrayList<Double>();
    public movingAveragePrediction(Stock stock, double money){
        this.stockPrice = stock.getPrice();
        this.money = money;
        this.movingAverage = stock.getMovingAverage();
    }
    public double movingAverageRun(){
        boolean bought = false;
        double startMoney = money;
        for(int i = 0; i <stockPrice.size(); i++)
        {
            if(stockPrice.get(i) > movingAverage.get(i) && movingAverage.get(i)!=-1 && bought==false && money >= stockPrice.get(i))
            {
                money-= stockPrice.get(i);
                shares++;
                bought = true;
                //System.out.println("BOUGHT STOCK! " + "NEW MONEY: " + money + " STOCK PRICE: " + stockPrice.get(i) + " MOVING AVERAGE: " + movingAverage.get(i));

            }
            if(stockPrice.get(i) < movingAverage.get(i) && movingAverage.get(i)!=-1 && bought==true && money+ stockPrice.get(i) >0)
            {
                money+= stockPrice.get(i);
                bought = false;
                //System.out.println("SOLD STOCK! " + "NEW MONEY: " + money + " STOCK PRICE: " + stockPrice.get(i) + " MOVING AVERAGE: " + movingAverage.get(i));
                shares--;
            }
            if(stockPrice.size()-1 == i)
            {
                money+= stockPrice.get(i);
                bought = false;
                //System.out.println("SOLD STOCK! " + "NEW MONEY: " + money + " STOCK PRICE: " + stockPrice.get(i) + " MOVING AVERAGE: " + movingAverage.get(i));
                shares--;
            }

        }
        return money-startMoney;
    }

//    public double run500(Stock stock)
//    {
//        int highestMATerm = -1;
//        int lowestMATerm = -1;
//        double highestMA = movingAverageRun();
//        double lowestMA = movingAverageRun();
//        for(int i =1; i <= 500; i++){
//            stock.setMovingAverageTerm(i);
//            System.out.println("Profit ("+i+"): " + movingAverageRun());
//            if(movingAverageRun() >highestMA)
//            {
//                highestMA = movingAverageRun();
//                highestMATerm = i;
//            }
//            if(movingAverageRun() <lowestMA)
//            {
//                lowestMA = movingAverageRun();
//                lowestMATerm = i;
//            }
//        }
//        return
//
//    }


}
