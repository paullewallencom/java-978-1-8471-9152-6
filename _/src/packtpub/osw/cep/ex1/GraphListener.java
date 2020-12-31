package packtpub.osw.cep.ex1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.esper.client.UpdateListener;
import net.esper.event.EventBean;

/**
 * Simple listener that prints the event to console.
 */
public class GraphListener implements UpdateListener {
	public static List graphValues = new ArrayList(50);
	public void update(EventBean[] newEvent, EventBean[] oldEvent) {
		for (int i = 0; i < newEvent.length; i++) {
			EventBean bean = newEvent[i];
			Map eventMap = (Map)bean.getUnderlying();
			GraphData gd = new GraphData(new Date(), (Long)eventMap.get("value"), (String)eventMap.get("name"));
			if(graphValues.size() > 50){
				synchronized (graphValues) {
					System.out.println("removing");
					graphValues.remove(0);	
				}
			}
			graphValues.add(gd);
		}
	}
}

class GraphData{
	private Date timestamp;
	private double value;
	private String category;
	/**
	 * Constructor.
	 * @param timestamp
	 * @param value
	 * @param category
	 */
	GraphData(Date timestamp, double value, String category) {
		super();
		this.timestamp = timestamp;
		this.value = value;
		this.category = category;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}
	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}
}