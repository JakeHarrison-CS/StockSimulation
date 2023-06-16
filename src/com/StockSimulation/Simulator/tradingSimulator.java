/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.StockSimulation.Simulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jakeharrison
 */
public class tradingSimulator {
    public int shares;
    public double startMoney;
    public String stockChoice;

    private Stock stock;

    private ArrayList<Double> stockPriceList;

    public tradingSimulator(String stockChoice, int movingAverage){
        this.stockChoice= stockChoice;
        stock = new Stock(stockChoice, "null", movingAverage);
        this.stockPriceList = stock.getPrice();
    }

    public double currentPrice(int i){
        return stockPriceList.get(i);
    }

    public double buy(double price, int amount, double money){
        if((price*amount) <= money){
            money-=(price*amount);
            changeShares(amount);
            return money;
        }
        else{
            // If we cant buy return -1 to handle in the stockTrader file
            return -1;
        }
    }

    public double sell(double price, int amount, double money){
        money+=(price*amount);
        //Convert the shares to a negative, so we can remove it from the shares
        changeShares((amount*-1));
        return money;
    }

    public int changeShares(int amount){
        shares+=amount;
        return shares;
    }

    public int getShares(){
        return shares;
    }

    public ArrayList getMovingAverage(){
        ArrayList<Double> movingAverage = stock.getMovingAverage();
        System.out.println(Arrays.toString(movingAverage.toArray()));
        return movingAverage;
    }





}
