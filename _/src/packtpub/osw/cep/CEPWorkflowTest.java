package packtpub.osw.cep;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.basic.BasicWorkflow;
import com.opensymphony.workflow.config.Configuration;
import com.opensymphony.workflow.config.DefaultConfiguration;

/**
 * Executes the CEP example workflow.
 */
public class CEPWorkflowTest {

	public static void main(String[] args) {
		Workflow workflow = new BasicWorkflow("test");
		Configuration config = new DefaultConfiguration();
		workflow.setConfiguration(config);
		try {
			Map inputs = new HashMap();
			inputs.put("customerId", "123");
			inputs.put("amount", new Float(10000f));
			long workflowId = workflow.initialize("cep", 100, Collections.EMPTY_MAP);
			workflow.doAction(workflowId, 1, inputs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}