package packtpub.osw.cep;

import java.util.Iterator;

import net.esper.client.EPAdministrator;
import net.esper.client.EPRuntime;
import net.esper.client.EPServiceProvider;
import net.esper.client.EPServiceProviderManager;
import net.esper.client.EPStatement;
import net.esper.event.EventBean;

/**
 * CEP Pull model example.
 */
public class CepCountTest {

	public static void main(String[] args) {
		EPServiceProvider epService = EPServiceProviderManager
				.getDefaultProvider();
		EPAdministrator admin = epService.getEPAdministrator();
		EPStatement eql = admin
				.createEQL("select count(*) as c from packtpub.osw.cep.Deposit");
		Deposit d = new Deposit();
		d.setAmount(100);
		d.setCustomerId("123");
		EPRuntime runtime = epService.getEPRuntime();
		runtime.sendEvent(d);
		Iterator<EventBean> it = eql.iterator();
		System.out.println(it.next().get("c"));
	}
}
