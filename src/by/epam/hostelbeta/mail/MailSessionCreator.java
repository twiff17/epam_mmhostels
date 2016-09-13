package by.epam.hostelbeta.mail;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * The Class MailSessionCreator. Creates a new mail session for sending messages
 * to the client
 */
public class MailSessionCreator {

	/** The smtp host. */
	private String smtpHost;

	/** The smtp port. */
	private String smtpPort;

	/** The user name. */
	private String userName;

	/** The user password. */
	private String userPassword;

	/** The session properties. */
	private Properties sessionProperties;

	/**
	 * Instantiates a new mail session creator.
	 *
	 * @param configProperties
	 *            the config properties
	 */
	public MailSessionCreator(Properties configProperties) {
		smtpHost = configProperties.getProperty("mail.smtp.host");
		smtpPort = configProperties.getProperty("mail.smtp.port");
		userName = configProperties.getProperty("mail.user.name");
		userPassword = configProperties.getProperty("mail.user.password");

		sessionProperties = new Properties();
		sessionProperties.setProperty("mail.transport.protocol", "smtp");
		sessionProperties.setProperty("mail.host", smtpHost);
		sessionProperties.put("mail.smtp.auth", "true");
		sessionProperties.put("mail.debug", "true");
		sessionProperties.put("mail.smtp.starttls.enable", "true");
		sessionProperties.put("mail.smtp.port", smtpPort);
		sessionProperties.put("mail.smtp.socketFactory.port", smtpPort);
		sessionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		sessionProperties.put("mail.smtp.socketFactory.fallback", "false");
		sessionProperties.setProperty("mail.smtp.quitwait", "false");
	}

	/**
	 * Creates a new mail session.
	 *
	 * @return created session
	 */
	public Session createSession() {
		return Session.getDefaultInstance(sessionProperties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, userPassword);
			}
		});
	}
}
