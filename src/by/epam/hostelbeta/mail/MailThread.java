package by.epam.hostelbeta.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MailThread extends Thread {
	static final Logger LOGGER = LogManager.getLogger(MailThread.class);

	private MimeMessage message;
	private String sendToEmail;
	private String mailSubject;
	private String mailText;
	private Properties properties;

	public MailThread(String sendToEmail, String mailSubject, String mailText, Properties properties) {
		this.sendToEmail = sendToEmail;
		this.mailSubject = mailSubject;
		this.mailText = mailText;
		this.properties = properties;
	}

	private void init() {
		Session mailSession = (new MailSessionCreator(properties)).createSession();
		mailSession.setDebug(true);

		message = new MimeMessage(mailSession);
		try {
			message.setSubject(mailSubject);
			message.setText(mailText, "UTF-8");
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
		} catch (AddressException e) {
			LOGGER.error("Incorrect address: " + sendToEmail + " " + e);
		} catch (MessagingException e) {
			LOGGER.error("Error creating message" + e);
		}
	}

	public void run() {
		init();
		try {
			Transport.send(message);
			LOGGER.info("Message is sent successfully");
		} catch (MessagingException e) {
			LOGGER.error("Error sending message " + e);
		}
	}
}
