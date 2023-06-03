package com.StockSimulation.Simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;



public class Stock {
    private final String tickerSymbol;
    private ArrayList<Double> movingAverage= new ArrayList<Double>();
    private ArrayList<Double> stockPrice = new ArrayList<>();

    private int movingAverageTerm;



    private final String file;

    /**
     * Creates a new stock object and sets the values (stock price, moving average)
     * @param file (String) the name of the stock data file
     * @param tickerSymbol (String) the ticker symbol of the stock
     * @param movingAverageTerm (int) the term for the moving average
     */
    public Stock(String file, String tickerSymbol, int movingAverageTerm){
        this.file = file;
        this.tickerSymbol = tickerSymbol;
        this.movingAverageTerm = movingAverageTerm;
        readingData("price", stockPrice);
        Collections.reverse(stockPrice);
        for(int i =0; i < movingAverageTerm; i++)
        {
            movingAverage.add(-1.0);
        }
        readingData("moving average", movingAverage);


    }

    /**
     * Creates a new stock object and sets the values (stock price, moving average) (uses 20 as the default moving average term)
     * @param file (String) the name of the stock data file
     * @param tickerSymbol (String) the ticker symbol of the stock
     */
    public Stock(String file, String tickerSymbol) {
        this.file = file;
        this.tickerSymbol = tickerSymbol;
        this.movingAverageTerm = 20;
        readingData("price", stockPrice);
        Collections.reverse(stockPrice);
        for(int i =0; i < movingAverageTerm; i++)
        {
            movingAverage.add(-1.0);
        }
        readingData("moving average", movingAverage);
    }

    /**
     * Gets the ArrayList of prices of the stock
     * @return Returns stock price ArrayList
     */
    public ArrayList getPrice()
    {
        return stockPrice;
    }

    /**
     * get the ArrayList of moving averages of the stock
     * @return returns moving average arraylist
     */
    public ArrayList getMovingAverage()
    {
        return movingAverage;
    }

    /**
     * Allows the user to set the new moving average term then recalculates the moving average
     * @param movingAverageTerm (int) the new moving average term number
     */
    public void setMovingAverageTerm(int movingAverageTerm)
    {
        this.movingAverageTerm = movingAverageTerm;
        movingAverage.clear();
        for(int i =0; i < movingAverageTerm; i++)
        {
            movingAverage.add(-1.0);
        }
        readingData("moving average", movingAverage);

    }

    /**
     * Makes the graph
     */
    public void makeGraph() {
        ChartDisplay stock = new ChartDisplay((file + "(" + tickerSymbol+ ") " + "Stock Price"), "Price (USD)", true);
        stock.updateData(stockPrice, movingAverage, true);


    }

    /**
     * This method either reads, one of the CSV files and grabs the closing price, or calcualtes the moving average
     * @param choice (String), if the string is "price" it will calculate the price, if it is "moving average" it will calculate the moving avaerage
     * @param choiceList (ArrayList) The list it should add the info to
     */
    private void readingData(String choice, ArrayList choiceList) {
        String csvFile = "StockData/" +file + ".csv";
        String line = "";
        String cvsSplitBy = ",";
        int place = 0;
        int counter = 0;
        if(choice == "price" || choice == "Price")
        {
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile)))
            {

                while ((line = br.readLine()) != null)
                {

                    // use comma as separator
                    String[] data = line.split(cvsSplitBy);
                    if (place != 0)
                    {
                        //System.out.println("Got Price: " + counter);
                        counter++;
                        choiceList.add(Double.parseDouble(data[1]));
                    }
                    //System.out.println((data[1]) + " | " + data[0]);
                    place++;
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        } else if (choice.equalsIgnoreCase("moving average")) {
            double movingAverageCalc;
            for (int i = 0; i < stockPrice.size(); i++) {
                if (i >= (movingAverageTerm-1)) {
                    movingAverageCalc = 0;
                    for (int j = i - (movingAverageTerm-1); j <= i; j++) {
                        if(stockPrice.get(j) >0)
                        {
                            movingAverageCalc += stockPrice.get(j);
                        }
                    }
                    movingAverageCalc = movingAverageCalc / movingAverageTerm;
                    choiceList.add(movingAverageCalc);
                }
            }
        }

    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}


