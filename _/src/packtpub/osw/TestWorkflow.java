/**
 * 
 */
package packtpub.osw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.basic.BasicWorkflow;
import com.opensymphony.workflow.config.Configuration;
import com.opensymphony.workflow.config.DefaultConfiguration;
import com.opensymphony.workflow.loader.ActionDescriptor;
import com.opensymphony.workflow.loader.WorkflowDescriptor;
import com.opensymphony.workflow.spi.Step;
import com.opensymphony.workflow.spi.WorkflowEntry;

/**
 * @author Naya
 * @version 1.0
 */
public class TestWorkflow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Workflow workflow = new BasicWorkflow("test");
		Configuration config = new DefaultConfiguration();
		workflow.setConfiguration(config);
		System.out.println("available workflows:" + Arrays.toString(workflow.getWorkflowNames()));
		try {
			final String wfName = "example";
			long workflowId = workflow
					.initialize(wfName, 100, new HashMap());
			BufferedReader r = new BufferedReader(new InputStreamReader(
					System.in));
			while (true) {
				int[] actions = workflow.getAvailableActions(workflowId, null);
				WorkflowDescriptor wd = workflow.getWorkflowDescriptor(workflow
						.getWorkflowName(workflowId));
				System.out.println("AVAILABLE ACTIONS:");
				for (int i = 0; i < actions.length; i++) {
					String name = wd.getAction(actions[i]).getName();
					long id = wd.getAction(actions[i]).getId();
					System.out.println("action=" + name + ";id=" + id);
				}

				String line = r.readLine();
				if (line != null) {
					workflow.doAction(workflowId, Integer.parseInt(line),
							Collections.EMPTY_MAP);
					describe(workflow.getHistorySteps(workflowId), workflow.getWorkflowDescriptor(wfName));
					int state = workflow.getEntryState(workflowId);
					if (state == WorkflowEntry.COMPLETED) {
						System.out.println("Completed");
						System.exit(0);
					}
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void describe(List steps, WorkflowDescriptor wd) {
		System.out.println("WORKFLOW HISTORY:\n");
		for (Iterator iterator = steps.iterator(); iterator.hasNext();) {
			Step step = (Step) iterator.next();
			String owner = step.getOwner();
			ActionDescriptor action = wd.getAction(step.getActionId());
			synchronized(System.out){
				System.out.println("Step:" + wd.getStep(step.getStepId()).getName() + "-id:" + step.getId() );
				System.out.println("Action:" + (action == null ? "NONE" : action.getName()));
				System.out.println("Status:" + step.getStatus());
				System.out.println("Start Date:" + step.getStartDate());
				System.out.println("Finish Date:" + step.getFinishDate());
				System.out.println("Owner: " + owner );	
			}
			long[] prevIds = step.getPreviousStepIds();
			if (prevIds != null) {
				for (int i = 0; i < prevIds.length; i++) {
					long prevId = prevIds[i];
					System.out.print(prevId + ", ");
				}
			} else {
				System.out.print("none");
			}

		}
	}
}