package com.scienceminer.chatgpt;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MercuryOrbitPlot {

    public static void main(String[] args) {
        
        double G = 6.674e-11;       // gravitational constant
        double M = 1.9891e30;       // mass of the sun
        double m = 3.285e23;        // mass of Mercury
        double r0 = 46e9;           // initial distance from the sun
        double v0 = 58.98e3;        // initial velocity
        double x0 = r0;             // initial x position
        double y0 = 0;              // initial y position
        double vx0 = 0;             // initial x velocity
        double vy0 = v0;            // initial y velocity
        double dt = 3600;           // time step in seconds
        double tmax = 87.9691*24*3600;   // total time for one orbit in seconds
        double x, y, r, ax, ay, vx, vy, t;
        XYSeries series = new XYSeries("Mercury Orbit");
        
        // simulate orbit of Mercury
        x = x0;
        y = y0;
        vx = vx0;
        vy = vy0;
        t = 0;
        while (t < tmax) {
            r = Math.sqrt(x*x + y*y);
            ax = -G*M*x/(r*r*r);
            ay = -G*M*y/(r*r*r);
            vx += ax*dt;
            vy += ay*dt;
            x += vx*dt;
            y += vy*dt;
            series.add(x, y);
            t += dt;
        }
        
        // create plot
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createScatterPlot(
            "Orbit of Mercury",
            "X",
            "Y",
            dataset,
            PlotOrientation.VERTICAL,
            false,
            false,
            false
        );
        
        // display plot
        ChartFrame frame = new ChartFrame("Orbit of Mercury", chart);
        frame.pack();
        frame.setVisible(true);
    }

}
