package com.StockSimulation.Simulator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import javax.swing.JFrame;
import java.awt.Color;
import java.util.List;


public class ChartDisplay {
    private static String CHART_TITLE;
    private static String Y_AXIS_LABEL;

    private XYSeries series1;
    private XYSeries series2; // New series for the second line
    private XYSeriesCollection dataset;
    private JFreeChart chart;
    public ChartPanel chartPanel;

    /**
     * Creates a new chart display object
     * @param CHART_TITLE (String) the title of the chart
     * @param Y_AXIS_LABEL (String) the label for the y-axis
     * @param enableSecondLine (boolean) whether or not to enable the second line
     */
    public ChartDisplay(String CHART_TITLE, String Y_AXIS_LABEL, boolean enableSecondLine) {
        this.CHART_TITLE = CHART_TITLE;
        this.Y_AXIS_LABEL = Y_AXIS_LABEL;
        series1 = new XYSeries("Stock Price");
        dataset = new XYSeriesCollection(series1);
        chart = ChartFactory.createXYLineChart(CHART_TITLE, null, Y_AXIS_LABEL, dataset);
        chart.getLegend().setVisible(false);

        Plot plot = chart.getPlot();
        ((Plot) plot).setBackgroundPaint(new Color(39, 44, 60)); // Set chart background color to black

        XYPlot xyPlot = (XYPlot) plot;
        xyPlot.setRangeGridlinePaint(new Color(24, 65, 107)); // Set hints of green for range gridlines
        // Set the colors for the axis
        xyPlot.getDomainAxis().setAxisLinePaint(new Color(255, 255, 255)); // White color for the x-axis line
        xyPlot.getDomainAxis().setTickMarkPaint(new Color(255, 255, 255)); // White color for the x-axis tick marks
        xyPlot.getRangeAxis().setAxisLinePaint(new Color(255, 255, 255)); // White color for the y-axis line
        xyPlot.getRangeAxis().setTickMarkPaint(new Color(255, 255, 255)); // White color for the y-axis tick marks
        ValueAxis domainAxis = xyPlot.getDomainAxis();
        domainAxis.setLabel("");
        domainAxis.setTickLabelsVisible(false);
        xyPlot.setDomainAxis(domainAxis);
        xyPlot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);

        if (enableSecondLine) {
            series2 = new XYSeries("Moving Average");
            dataset.addSeries(series2);

            // Set the color of the second line to green
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) xyPlot.getRenderer();
            renderer.setSeriesPaint(1, new Color(0, 255, 0)); // Set green color for the second line
        }

        chartPanel = new ChartPanel(chart);

        JFrame frame = new JFrame( "Stock Chart");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }


    /**
     * Updates the data in the chart
     * @param data1 (List<Double>) the data for the first line
     * @param data2 (List<Double>) the data for the second line
     * @param addBulletPoints (boolean) whether or not to add bullet points to the second line
     */
    public void updateData(List<Double> data1, List<Double> data2, boolean addBulletPoints) {
        series1.clear();
        if (series2 != null) {
            series2.clear();
        }

        int xIndex = 0;
        for (Double value : data1) {
            series1.add(xIndex, value);
            xIndex++;
        }

        if (series2 != null && addBulletPoints) {
            xIndex = 0;

                for (Double value : data2) {
                    if(value > -0.1)
                    {
                        series2.add(xIndex, value);
                    }
                    xIndex++;
            }
        }

        chartPanel.repaint();
    }

}
