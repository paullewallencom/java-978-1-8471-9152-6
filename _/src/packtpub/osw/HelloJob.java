package packtpub.osw;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

/**
 * Hello world job.
 */
public class HelloJob implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(arg0.getJobDetail().getJobDataMap().getString("test"));
		try {
			arg0.getScheduler().unscheduleJob("myTrigger", null);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hello Quartz world.");
	}
}
