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
public class ContinousCEPTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EPServiceProvider epService = EPServiceProviderManager
				.getDefaultProvider();
		EPAdministrator admin = epService.getEPAdministrator();
		EPStatement pattern = admin
				.createEQL("Select customerId from packtpub.osw.cep.Deposit where amount > 1000");
		PrintListener listener = new PrintListener();
		pattern.addListener(listener);
		//Select customerId, sum(amount) from packtpub.osw.cep.Deposit group by customerId
		// group by customerId
		//"Select customerId, amount from packtpub.osw.cep.Deposit.win:time(60 sec) as d group by customerId"
		//select count(*) as cuenta FROM packtpub.osw.cep.Deposit.win:time(60 sec)
		//.win:length(10)
//group by customerId
		//(customerId != null)
		//Select customerId, sum(amount), avg(amount) from packtpub.osw.cep.Deposit group by customerId output every 2 events
// output every 2 events
		for (int i = 0; i < 2; i++) {
		for (int j = 0; j < 10; j++) {
			Deposit d = new Deposit();
			d.setAmount(100);
			d.setCustomerId(Long.toString(j));
			EPRuntime runtime = epService.getEPRuntime();
			runtime.sendEvent(d);	
		}
	}
		
		

	}

}
