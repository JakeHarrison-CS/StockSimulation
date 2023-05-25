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
    private static final String CHART_TITLE = "Real-time Chart";
    private static final String X_AXIS_LABEL = "X-axis";
    private static final String Y_AXIS_LABEL = "Y-axis";

    private XYSeries series;
    private XYSeriesCollection dataset;
    private JFreeChart chart;
    private ChartPanel chartPanel;

    public ChartDisplay (List<Double> data) {
        series = new XYSeries("Real-time Data");
        dataset = new XYSeriesCollection(series);
        chart = ChartFactory.createXYLineChart(CHART_TITLE, X_AXIS_LABEL, Y_AXIS_LABEL, dataset);
        chart.getLegend().setVisible(false);
        chartPanel = new ChartPanel(chart);

        JFrame frame = new JFrame("Real-time Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);

        updateData(data);
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
