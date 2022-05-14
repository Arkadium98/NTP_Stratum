/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectosegundoparcial;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.util.ArrayList;

import java.awt.Font;
import java.awt.Paint;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import static proyectosegundoparcial.GraficaBarrasStratum.createChart;
import static proyectosegundoparcial.GraficaBarrasStratum.createDataset;

public class GraficaBarrasPrecision extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title the frame title.
     */
    static ArrayList<DatoIndividual> stratArray; // <----

    public GraficaBarrasPrecision(String title, ArrayList<DatoIndividual> arr) {
        super(title);
        stratArray = arr; /// <-----
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(1500, 1000));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static CategoryDataset createDataset() {

        // column keys...
        //String category1 = Integer.toString(obj.Strat); // borrar
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (DatoIndividual obj : stratArray) {
            dataset.addValue(obj.Prec, Integer.toString(obj.Strat), obj.Nombre);
        }

        return dataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset the dataset.
     *
     * @return A chart.
     */
    static JFreeChart createChart(CategoryDataset dataset) {
        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
                "Comparacion de la Precision por Servidores", // chart title
                "Categoria", // domain axis label
                "Precision", // range axis label
                dataset, // data
                PlotOrientation.HORIZONTAL, // orientation
                false, // include legend
                true,
                false
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        plot.setRangePannable(true);

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setItemLabelAnchorOffset(9.0);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelGenerator(
                new StandardCategoryItemLabelGenerator());
        renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator(
                "{0}, {1}) = {2} per 100,000", new DecimalFormat("0")));
        renderer.setDrawBarOutline(true);
        //renderer.setBaseOutlinePaint(Color.white);
        //renderer.setBaseOutlineStroke(new BasicStroke(0.5f));

        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setCategoryMargin(0.25);
        categoryAxis.setUpperMargin(0.02);
        categoryAxis.setLowerMargin(0.02);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setUpperMargin(0.10);
        ChartUtilities.applyCurrentTheme(chart);

        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        return panel;
    }

    public static ByteArrayOutputStream createImage() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        JFreeChart chart = createChart(createDataset());
        ChartUtilities.writeChartAsPNG(out, chart, 500, 500);
        return out;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {

    }

}
