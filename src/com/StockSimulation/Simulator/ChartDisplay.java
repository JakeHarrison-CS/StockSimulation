package com.StockSimulation.Simulator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JFrame;
import java.util.List;

public class ChartDisplay {
    private static String CHART_TITLE;
    private static String X_AXIS_LABEL;
    private static String Y_AXIS_LABEL;

    private XYSeries series;
    private XYSeriesCollection dataset;
    private JFreeChart chart;
    private ChartPanel chartPanel;

    public ChartDisplay (String CHART_TITLE, String X_AXIS_LABEL, String Y_AXIS_LABEL) {
        this.CHART_TITLE = CHART_TITLE;
        this.X_AXIS_LABEL = X_AXIS_LABEL;
        this.Y_AXIS_LABEL = Y_AXIS_LABEL;
        series = new XYSeries("Stock Price");
        dataset = new XYSeriesCollection(series);
        chart = ChartFactory.createXYLineChart(CHART_TITLE, X_AXIS_LABEL, Y_AXIS_LABEL, dataset);
        chart.getLegend().setVisible(false);
        chartPanel = new ChartPanel(chart);

        JFrame frame = new JFrame("Real-time Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void updateData(List<Double> data) {
        series.clear();
        int xIndex = 0;
        for (Double value : data) {
            series.add(xIndex++, value);
        }
        chartPanel.repaint();
    }
}
