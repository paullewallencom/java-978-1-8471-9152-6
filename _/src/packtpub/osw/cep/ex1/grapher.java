package packtpub.osw.cep.ex1;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.esper.client.EPAdministrator;
import net.esper.client.EPRuntime;
import net.esper.client.EPServiceProvider;
import net.esper.client.EPServiceProviderManager;
import net.esper.client.EPStatement;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.basic.BasicWorkflow;
import com.opensymphony.workflow.config.Configuration;
import com.opensymphony.workflow.config.DefaultConfiguration;

import packtpub.osw.cep.CEPWorkflowTest;

/**
 * CEP Example1
 */
public class grapher {

	public static void main(String[] args) {
		graph();

	}

	private static JFrame jf;

	private static JLabel lblChart;

	private static void graph() {
		if (jf == null) {
			setupGraphicSubsystem();
		}

		DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
		categoryDataset.addValue(1.0D, "Underway", "holiday");
		categoryDataset.addValue(2.0D, "Finishing", "holiday");
		categoryDataset.addValue(3.0D, "Underway", "example");
		categoryDataset.addValue(4.0D, "Finishing", "example");
		JFreeChart chart = ChartFactory.createBarChart("BAM - Grouped by status", "workflow status", "number of instances", categoryDataset, PlotOrientation.VERTICAL, true, true, true);
		BufferedImage image = chart.createBufferedImage(500, 300);
		lblChart.setIcon(new ImageIcon(image));
	}

	private static void setupGraphicSubsystem() {
		jf = new JFrame();
		jf.setBounds(new Rectangle(600, 400));
		lblChart = new JLabel();
		jf.add(lblChart);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

}
