/**
 * 
 */
package packtpub.osw;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;

import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.basic.BasicWorkflow;
import com.opensymphony.workflow.config.Configuration;
import com.opensymphony.workflow.config.DefaultConfiguration;
import com.opensymphony.workflow.spi.Step;

/**
 * Basic workflow testcase
 * @author Naya
 * @version 1.0
 */
public class WorkflowTestCase extends TestCase {

	protected Workflow workflow;

	protected long workflowId;

	public void setUp() {
		final String wfName = "holiday2";
		workflow = new BasicWorkflow("test");
		Configuration config = new DefaultConfiguration();
		workflow.setConfiguration(config);
		try {
			workflowId = workflow.initialize(wfName, 100, new HashMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testWorkflow() {
		try {
			List perms = workflow.getSecurityPermissions(workflowId, Collections.EMPTY_MAP);
			System.out.println(perms);
			int[] availableActions = workflow.getAvailableActions(workflowId,
					null);
			assertEquals("Unexpected number of available actions", 1,
					availableActions.length);
			assertEquals("Unexpected available action", 1, availableActions[0]);
			Collection currentSteps = workflow.getCurrentSteps(workflowId);
			assertEquals("Unexpected number of current steps", 1, currentSteps
					.size());
			Step currentStep = (Step) currentSteps.iterator().next();
			assertEquals("Unexpected current step", 1, currentStep.getStepId());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
