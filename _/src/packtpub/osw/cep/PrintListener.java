package packtpub.osw.cep;

import java.util.Map;
import java.util.Set;

import net.esper.client.UpdateListener;
import net.esper.event.EventBean;

/**
 * Simple listener that prints the event to console.
 */
public class PrintListener implements UpdateListener {

	public void update(EventBean[] newEvent, EventBean[] oldEvent) {
//		System.out.println(Arrays.toString(newEvent));
		for (int i = 0; i < newEvent.length; i++) {
			EventBean bean = newEvent[i];
			describeBean(bean);
		}
		if(oldEvent != null){
			System.out.println("-------- old data:");
			for (int i = 0; i < oldEvent.length; i++) {
				EventBean bean = oldEvent[i];
				describeBean(bean);
			}
		}
		System.out.println("--------------------------");
	}

	private void describeBean(EventBean bean) {
		Set keys = ((Map)bean.getUnderlying()).keySet();
		for (Object object : keys) {
			System.out.println(object + ":" + ((Map)bean.getUnderlying()).get(object));
		}
	}

}