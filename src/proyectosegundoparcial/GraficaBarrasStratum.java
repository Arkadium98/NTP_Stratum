/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectosegundoparcial;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultKeyedValues2DDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;



public class GraficaBarrasStratum extends ApplicationFrame {
    static ArrayList<PromedioStratum> stratArray;
    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     * @param arr
     */
    public GraficaBarrasStratum(String title, ArrayList<PromedioStratum> arr) {
        super(title);
        stratArray = arr;
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 300));
        setContentPane(chartPanel);
    }

    public static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createStackedBarChart(
            "Comparacion de Stratum",       // chart title
            "Categoria",               // domain axis label
            "Valor",                  // range axis label
            dataset,         // data
            PlotOrientation.HORIZONTAL,
            true,            // include legend
            true,            // tooltips
            false            // urls
        );
        
        
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainGridlinesVisible(true);
        plot.setRangeCrosshairVisible(true);
        plot.setRangeCrosshairPaint(Color.blue);

        // set the range axis to display integers only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        
        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, new Color(0, 0, 64));
        GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.red,
                0.0f, 0.0f, new Color(0, 64, 0));
        
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        
        
        return chart;
    }

    /**
     * Creates a dataset.
     *
     * @return A dataset.
     */
    public static CategoryDataset createDataset() {
        // row keys...
        String series1 = "Stratum 1";
        String series2 = "Stratum 2";
        String series3 = "Stratum 3";

        // column keys...
        String category1 = "Precision";
        String category2 = "Delay";
        
        DefaultKeyedValues2DDataset data = new DefaultKeyedValues2DDataset();
        // --------------- Llenar el Dataset --------------------
        for(PromedioStratum obj:stratArray){
            switch(obj.Strat){
                case 1:{
                        data.addValue(obj.Prec, category1, series1);
                        data.addValue(obj.Delay, category2, series1);
                }
                case 2:{
                        data.addValue(obj.Prec, category1, series2);
                        data.addValue(obj.Delay, category2, series2);
                }
                case 3:{
                        data.addValue(obj.Prec, category1, series3);
                        data.addValue(obj.Delay, category2, series3);
                }
            }
        }
        return data;
    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        return panel;
    }
     public static ByteArrayOutputStream createImagen() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        JFreeChart chart = createChart(createDataset());
        ChartUtilities.writeChartAsPNG(out, chart, 500, 500 );
        return out;
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        
    }

}