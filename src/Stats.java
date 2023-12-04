import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.util.ArrayList;


public class Stats {
    public final ArrayList<Double> bestFits;
    public final ArrayList<Double> averageFits;
    public final ArrayList<Double> stdDevFits;
    public final ArrayList<Integer> numberOfFitnessEvaluations;
    public final ArrayList<Integer> numberOfGenerations;

    public Stats() {
        this.bestFits = new ArrayList<>();
        this.averageFits = new ArrayList<>();
        this.stdDevFits = new ArrayList<>();
        this.numberOfFitnessEvaluations = new ArrayList<>();
        this.numberOfGenerations = new ArrayList<>();
    }

    public void plotEvolution() {
        XYSeriesCollection dataset = getXySeriesCollection();

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Evolution",
                "Generation",
                "Fitness",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );


        // Save chart as PNG
        try  {
            File file = new File("evolution.png");
            ChartUtilities.saveChartAsPNG(file, chart, 800, 600);
        } catch (Exception e) {
            System.out.println("Error while saving chart as PNG");
        }


    }

    private XYSeriesCollection getXySeriesCollection() {
        XYSeries bestFitSeries = new XYSeries("Best Fit");
        for (int i = 0; i < bestFits.size(); i++) {
            bestFitSeries.add(i, bestFits.get(i));
        }

        XYSeries averageFitSeries = new XYSeries("Average Fit");
        for (int i = 0; i < averageFits.size(); i++) {
            averageFitSeries.add(i, averageFits.get(i));
        }

        XYSeries stdDevFitSeries = new XYSeries("Standard Deviation Fit");
        for (int i = 0; i < stdDevFits.size(); i++) {
            stdDevFitSeries.add(i, stdDevFits.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(bestFitSeries);
        dataset.addSeries(averageFitSeries);
        dataset.addSeries(stdDevFitSeries);
        return dataset;
    }

}
