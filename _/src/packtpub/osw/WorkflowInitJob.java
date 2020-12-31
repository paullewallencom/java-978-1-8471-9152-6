package packtpub.osw;

import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.basic.BasicWorkflow;
import com.opensymphony.workflow.config.Configuration;
import com.opensymphony.workflow.config.DefaultConfiguration;

/**
 * Creates a new workflow instance.
 */
public class WorkflowInitJob implements Job {

	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		
		String userName = ctx.getJobDetail().getJobDataMap().getString("user");
		String wfName = ctx.getJobDetail().getJobDataMap().getString("wfName");
		Map inputs = (Map) ctx.getJobDetail().getJobDataMap().get("inputs");
		int initAction = ctx.getJobDetail().getJobDataMap().getInt("initAction");
		
		Workflow workflow = new BasicWorkflow(userName);
		Configuration config = new DefaultConfiguration();
		workflow.setConfiguration(config);
		
		try {
			long workflowId = workflow.initialize(wfName, initAction, inputs);
			System.out.println("Instantiated new workflow with id:" + workflowId);
		} catch (Exception e) {
			throw new JobExecutionException(e);
		} 
	}
}
