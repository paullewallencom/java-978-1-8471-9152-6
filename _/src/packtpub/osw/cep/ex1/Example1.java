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
public class Example1 {

	public static void main(String[] args) {
		setupEngine();
		Workflow workflow = new BasicWorkflow("test");
		Configuration config = new DefaultConfiguration();
		workflow.setConfiguration(config);
		try {
			while(true){
				long workflowId = workflow.initialize("cep-example1", 100,
						Collections.EMPTY_MAP);
				workflow.doAction(workflowId, 1, Collections.EMPTY_MAP);
				graph();	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void setupEngine() {
		EPServiceProvider epService = EPServiceProviderManager
				.getDefaultProvider();
		EPAdministrator admin = epService.getEPAdministrator();
		//creation rate
		EPStatement eql = admin
				.createEQL("select 'creation' as name,count(*) as value from packtpub.osw.cep.ex1.ProcessCreationEvent.win:time(1 minutes)");
		//completion rate
		EPStatement eql2 = admin
				.createEQL("select 'completion' as name,count(*) as value from packtpub.osw.cep.ex1.ProcessCompletionEvent.win:time(1 minutes)");
		//failure rate
		EPStatement eql3 = admin
				.createEQL("select 'failure' as name,count(*) as value from packtpub.osw.cep.ex1.ProcessFailureEvent.win:time(1 minutes)");
		GraphListener pl = new GraphListener();
		eql.addListener(pl);
		eql2.addListener(pl);
		eql3.addListener(pl);
	}

	private static JFrame jf;

	private static JLabel lblChart;

	private static void graph() {
		if (jf == null) {
			setupGraphicSubsystem();
		}

		TimeSeriesCollection categoryDataset = setupChartData();

		JFreeChart chart = ChartFactory.createTimeSeriesChart("Event Chart", // Title
				"KPIs", // X-Axis label
				"Number of Event", // Y-Axis label
				categoryDataset, // Dataset
				true // Show legend
				, true, true);
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

	private static TimeSeriesCollection setupChartData() {
		TimeSeriesCollection categoryDataset = new TimeSeriesCollection();
		TimeSeries tsc = new TimeSeries("Creation", Second.class);
		TimeSeries tsf = new TimeSeries("Failure", Second.class);
		TimeSeries tsp = new TimeSeries("Completion", Second.class);
		categoryDataset.addSeries(tsp);
		categoryDataset.addSeries(tsc);
		categoryDataset.addSeries(tsf);

		for (Iterator iter = GraphListener.graphValues.iterator(); iter
				.hasNext();) {
			GraphData data = (GraphData) iter.next();
			if (data.getCategory().equalsIgnoreCase("Creation")) {
				tsc.addOrUpdate(new Second(data.getTimestamp()), data
						.getValue());
			}
			if (data.getCategory().equalsIgnoreCase("Failure")) {
				tsf.addOrUpdate(new Second(data.getTimestamp()), data
						.getValue());
			}
			if (data.getCategory().equalsIgnoreCase("Completion")) {
				tsp.addOrUpdate(new Second(data.getTimestamp()), data
						.getValue());
			}
		}
		return categoryDataset;
	}
}
