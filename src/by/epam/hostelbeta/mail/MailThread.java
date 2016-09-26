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

/**
 * The Class MailThread. Thread for sending mail to the client
 */
public class MailThread extends Thread {

	/** The Constant LOGGER. */
	static final Logger LOGGER = LogManager.getLogger(MailThread.class);

	/** The message. */
	private MimeMessage message;

	/** The send to email. */
	private String sendToEmail;

	/** The mail subject. */
	private String mailSubject;

	/** The mail text. */
	private String mailText;

	/** The properties. */
	private Properties properties;

	/**
	 * Instantiates a new mail thread.
	 *
	 * @param sendToEmail
	 *            receiver of the message
	 * @param mailSubject
	 *            the mail subject
	 * @param mailText
	 *            the mail text
	 * @param properties
	 *            the properties
	 */
	public MailThread(String sendToEmail, String mailSubject, String mailText, Properties properties) {
		this.sendToEmail = sendToEmail;
		this.mailSubject = mailSubject;
		this.mailText = mailText;
		this.properties = properties;
	}

	/**
	 * Inits the message.
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		init();
		try {
			Transport.send(message);
			LOGGER.info("Message is sent successfully");
		} catch (MessagingException e) {
			LOGGER.info("Couldn't send message to the client - " + sendToEmail);
		}
	}
}
