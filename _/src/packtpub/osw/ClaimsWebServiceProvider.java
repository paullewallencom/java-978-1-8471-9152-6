package packtpub.osw;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.WorkflowException;
import com.opensymphony.workflow.basic.BasicWorkflow;
import com.opensymphony.workflow.config.Configuration;
import com.opensymphony.workflow.config.DefaultConfiguration;

/**
 * Gets unpaid services data from web service and
 * creates a new customer contact workflow for each one.
 */
public class ClaimsWebServiceProvider implements FunctionProvider {

	public void execute(Map arg0, Map arg1, PropertySet arg2)
			throws WorkflowException {
		
		Workflow workflow = new BasicWorkflow("test");
		Configuration config = new DefaultConfiguration();
		workflow.setConfiguration(config);
		List unpaidData = getUnpaidDataFromWebService();
		for (Iterator iter = unpaidData.iterator(); iter.hasNext();) {
			UnpaidService service = (UnpaidService) iter.next();
			Map serviceData = serviceToMap(service);
			workflow.initialize("customer-contact", 100, serviceData);	
		}
		
	}

	/**
	 * @param service
	 * @return
	 */
	private Map serviceToMap(UnpaidService service) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	private List getUnpaidDataFromWebService() {
		// TODO Auto-generated method stub
		return null;
	}

}
