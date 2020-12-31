/**
 * 
 */
package packtpub.osw;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.basic.BasicWorkflow;
import com.opensymphony.workflow.config.Configuration;
import com.opensymphony.workflow.config.DefaultConfiguration;
import com.opensymphony.workflow.loader.ActionDescriptor;
import com.opensymphony.workflow.loader.WorkflowDescriptor;
import com.opensymphony.workflow.spi.Step;

/**
 * @author Naya
 * @version 1.0
 */
public class LoanWorkflow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Workflow workflow = new BasicWorkflow("test");
		Configuration config = new DefaultConfiguration();
		workflow.setConfiguration(config);
		try {
			Map inputs = new HashMap();
			LoanRequest r = new LoanRequest(1000, "Diego", true, 1000, false, false, false);
			inputs.put("loanRequest", r);
			long workflowId = workflow.initialize("loan-drools", 100, inputs);
			workflow.doAction(workflowId, 1, inputs);
			WorkflowDescriptor wd = workflow.getWorkflowDescriptor(workflow
					.getWorkflowName(workflowId));
			describe(workflow.getHistorySteps(workflowId), wd);
			describe(workflow.getCurrentSteps(workflowId), wd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void describe(List steps, WorkflowDescriptor wd) {
		System.out.println("WORKFLOW HISTORY:\n");
		for (Iterator iterator = steps.iterator(); iterator.hasNext();) {
			Step step = (Step) iterator.next();
			String owner = step.getOwner();
			ActionDescriptor action = wd.getAction(step.getActionId());
			synchronized (System.out) {
				System.out.println("Step:"
						+ wd.getStep(step.getStepId()).getName() + "-id:"
						+ step.getId());
				System.out.println("Action:"
						+ (action == null ? "NONE" : action.getName()));
				System.out.println("Status:" + step.getStatus());
				System.out.println("Start Date:" + step.getStartDate());
				System.out.println("Finish Date:" + step.getFinishDate());
				System.out.println("Owner: " + owner);
			}
			long[] prevIds = step.getPreviousStepIds();
			if (prevIds != null) {
				for (int i = 0; i < prevIds.length; i++) {
					long prevId = prevIds[i];
					System.out.println(prevId + ", ");
				}
			} else {
				System.out.println("none");
			}

		}
	}
}