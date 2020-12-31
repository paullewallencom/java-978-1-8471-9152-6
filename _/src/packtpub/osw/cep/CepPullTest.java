/**
 * 
 */
package packtpub.osw.cep;

import java.util.Iterator;

import net.esper.client.EPAdministrator;
import net.esper.client.EPRuntime;
import net.esper.client.EPServiceProvider;
import net.esper.client.EPServiceProviderManager;
import net.esper.client.EPStatement;
import net.esper.event.EventBean;

/**
 * @author Naya
 * @version 1.0
 */
public class CepPullTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
		EPAdministrator admin = epService.getEPAdministrator();
		EPStatement pattern = admin.createPattern("every A=packtpub.osw.cep.Deposit");
		Deposit d = new Deposit();
		d.setAmount(100);
		d.setCustomerId("123");
		EPRuntime runtime = epService.getEPRuntime();
		runtime.sendEvent(d);
		Iterator<EventBean> it = pattern.iterator();
		System.out.println("pulling.");
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}

}
