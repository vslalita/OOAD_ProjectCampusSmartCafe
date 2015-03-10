package com.csc.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;


public class PieChart1 extends JPanel {

	private static final long serialVersionUID = 1L;

	public PieChart1(String applicationTitle, String chartTitle) {
		// This will create the dataset 
		PieDataset dataset = createDataset();
		// based on the dataset we create the chart
		JFreeChart chart = createChart(dataset, chartTitle);
		// we put the chart into a panel
		ChartPanel chartPanel = new ChartPanel(chart);
		// default size
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		// add it to our application
//		setContentPane(chartPanel);

	}


	/**
	 * Creates a sample dataset 
	 */

	private  PieDataset createDataset() {
		DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("1-2-2015", 29);
		result.setValue("1-4-2015", 20);
		result.setValue("1-5-2015", 51);
		return result;

	}


	/**
	 * Creates a chart
	 */

	private JFreeChart createChart(PieDataset dataset, String title) {

		JFreeChart chart = ChartFactory.createPieChart3D(title,          // chart title
				dataset,                // data
				true,                   // include legend
				true,
				false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;

	}

//	public static void main(String[] args){
//		PieChart1 demo = new PieChart1("Comparison", "Which operating system are you using?");
//		//demo.pack();
//		demo.setVisible(true);
//	}
}

