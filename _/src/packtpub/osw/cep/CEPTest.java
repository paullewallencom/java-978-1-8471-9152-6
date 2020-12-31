/**
 * 
 */
package packtpub.osw.cep;

import net.esper.client.EPAdministrator;
import net.esper.client.EPRuntime;
import net.esper.client.EPServiceProvider;
import net.esper.client.EPServiceProviderManager;
import net.esper.client.EPStatement;

/**
 * @author Naya
 * @version 1.0
 */
public class CEPTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
		EPAdministrator admin = epService.getEPAdministrator();
//		EPStatement pattern = admin.createPattern("every A=packtpub.osw.cep.Deposit");
		EPStatement pattern = admin.createEQL("Select customerId, amount from packtpub.osw.cep.Deposit group by amount");
		PrintListener listener = new PrintListener();
		pattern.addListener(listener);
		Deposit d = new Deposit();
		d.setAmount(100);
		d.setCustomerId("123");
		EPRuntime runtime = epService.getEPRuntime();
		runtime.sendEvent(d);
		runtime.sendEvent(d);
	}

}
