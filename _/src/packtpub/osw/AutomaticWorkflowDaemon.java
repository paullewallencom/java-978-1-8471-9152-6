/**
 * 
 */
package packtpub.osw;

import java.util.Date;
import java.util.HashMap;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;

/**
 * @author Naya
 * @version 1.0
 */
public class AutomaticWorkflowDaemon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

		try {
			Scheduler sched = schedFact.getScheduler();
			sched.start();

			  JobDetail jobDetail = new JobDetail("myJob",
			                                      null,
			                                      WorkflowInitJob.class);
			  JobDataMap jdm = new JobDataMap();
			  jdm.put("user", "test");
			  jdm.put("wfName", "auto");
			  jdm.put("initAction", 100);
			  jdm.put("inputs", new HashMap());
			  jobDetail.setJobDataMap(jdm);

			  Trigger trigger = TriggerUtils.makeSecondlyTrigger(); // fire every hour
			  trigger.setStartTime(new Date());  // start on the next even hour
			  trigger.setName("myTrigger");

			  sched.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
