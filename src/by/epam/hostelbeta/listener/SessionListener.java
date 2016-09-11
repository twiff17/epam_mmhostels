package by.epam.hostelbeta.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving session events.
 * The class that is interested in processing a session
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addSessionListener<code> method. When
 * the session event occurs, that object's appropriate
 * method is invoked.
 *
 * @see SessionEvent
 */
@WebListener
public class SessionListener implements HttpSessionListener {
	
	/** The Constant SESSION_TIMEOUT. */
	private static final int SESSION_TIMEOUT = 1800;

	/**
	 * Instantiates a new session listener.
	 */
	public SessionListener() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent ev) {
		HttpSession session = ev.getSession();
		session.setMaxInactiveInterval(SESSION_TIMEOUT);
		session.setAttribute(Parameters.LOCALE, Parameters.DEFAULT);
		session.setAttribute(Parameters.LOCALE_MANAGER, LocaleManager.valueOf(Parameters.DEFAULT.toUpperCase()));
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent ev) {
	}

}
