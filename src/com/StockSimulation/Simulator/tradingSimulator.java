/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.StockSimulation.Simulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used to simulate the trading of a stock
 */
public class tradingSimulator {
    public int shares;
    public double startMoney;
    public String stockChoice;

    private Stock stock;

    private ArrayList<Double> stockPriceList;

    /**
     * Creates a new trading simulator object
     * @param stockChoice (String) the name of the stock
     * @param movingAverage (int) the term for the moving average
     */
    public tradingSimulator(String stockChoice, int movingAverage){
        this.stockChoice= stockChoice;
        stock = new Stock(stockChoice, "null", movingAverage);
        this.stockPriceList = stock.getPrice();
    }


    /**
     *  Gets the current price of the stock
     * @param i (int) the index of the stock price
     * @return (double) the current price of the stock
     */
    public double currentPrice(int i){
        return stockPriceList.get(i);
    }

    /**
     * Buys the stock
     * @param price (double) the price of the stock
     * @param amount (int) the amount of shares to buy
     * @param money (double) the amount of money the user has
     * @return (double) the amount of money the user has after buying the stock
     */
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

    /**
     * Sells the stock
     * @param price (double) the price of the stock
     * @param amount (int) the amount of shares to sell
     * @param money (double) the amount of money the user has
     * @return (double) the amount of money the user has after selling the stock
     */
    public double sell(double price, int amount, double money){
        money+=(price*amount);
        //Convert the shares to a negative, so we can remove it from the shares
        changeShares((amount*-1));
        return money;
    }

    /**
     * Changes the amount of shares the user has
     * @param amount (int) the amount of shares to change by
     * @return (int) the amount of shares the user has after changing the shares
     */
    public int changeShares(int amount){
        shares+=amount;
        return shares;
    }

    /**
     * Gets the amount of shares the user has
     * @return (int) the amount of shares the user has
     */
    public int getShares(){
        return shares;
    }

    /**
     * Gets the moving average of the stock
     * @return (ArrayList) the stock price list
     */
    public ArrayList getMovingAverage(){
        ArrayList<Double> movingAverage = stock.getMovingAverage();
        System.out.println(Arrays.toString(movingAverage.toArray()));
        return movingAverage;
    }





}
