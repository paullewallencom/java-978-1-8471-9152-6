package packtpub.osw;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.basic.BasicWorkflow;

/**
 * Quartz job that send an email if the specified workflow is active.
 */
public class SendMailIfActive implements Job {

	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		long wfId = ctx.getJobDetail().getJobDataMap().getLong("entryId");
		String username = ctx.getJobDetail().getJobDataMap().getString("username");
		String to = ctx.getJobDetail().getJobDataMap().getString("to");
		String from = ctx.getJobDetail().getJobDataMap().getString("from");
		String subject = ctx.getJobDetail().getJobDataMap().getString("subject");
		String text = ctx.getJobDetail().getJobDataMap().getString("text");
		String smtpHost = ctx.getJobDetail().getJobDataMap().getString("smtpHost");
		String triggerName = ctx.getJobDetail().getJobDataMap().getString("triggerName");
		String groupName = ctx.getJobDetail().getJobDataMap().getString("groupName");
		
		Workflow workflow = new BasicWorkflow(username);
		
		long state = workflow.getEntryState(wfId);
		System.out.println("State:" + state + " for wf:" + wfId);
		if(state != 4){
			sendMail(smtpHost, from, to, subject, text);
		}else{
			try {
				ctx.getScheduler().unscheduleJob(triggerName, groupName);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
	}

	private void sendMail(String smtpHost, String from, String to, String subject, String text){
	     Properties props = new Properties();
         props.put("mail.smtp.host", smtpHost);
         Session sendMailSession = Session.getInstance(props, null);
         try {
			Transport transport = sendMailSession.getTransport("smtp");
			 Message message = new MimeMessage(sendMailSession);
			 message.setFrom(new InternetAddress(from));
			 message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			 message.setSubject(subject);
			 message.setSentDate(new Date());
			 message.setText(text);
			 message.saveChanges();

			 transport.connect();
			 transport.sendMessage(message, message.getAllRecipients());
			 transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
