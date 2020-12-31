package packtpub.osw.cep;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import net.esper.client.UpdateListener;
import net.esper.event.EventBean;

/**
 * This listener send an email with the current data each time
 * in each invocation.
 */
public class SendMailListener implements UpdateListener {
	private String host;

	private String to;

	private String from;

	private String subject;

	private String bodyPrefix;

	/**
	 * Constructor.
	 * @param host
	 * @param to
	 * @param from
	 * @param subject
	 * @param bodyPrefix
	 */
	SendMailListener(String host, String to, String from, String subject,
			String bodyPrefix) {
		super();
		this.host = host;
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.bodyPrefix = bodyPrefix;
	}

	public void update(EventBean[] newData, EventBean[] oldData) {

		sendMail(host, to, from, subject, bodyPrefix + "\n" + newData);
	}

	private void sendMail(String host, String to, String from, String subject,
			String body) {
		Properties config = new Properties();
		config.put("mail.host", host);
		Session session = Session.getDefaultInstance(config);
		MimeMessage message = new MimeMessage(session);
		try {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message
					.addFrom(new InternetAddress[] { new InternetAddress(from) });
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}