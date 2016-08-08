package by.epam.hostelbeta.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

@WebListener
public class SessionListener implements HttpSessionListener {
	private static final int SESSION_TIMEOUT = 1800;
    public SessionListener() {
    }

    public void sessionCreated(HttpSessionEvent ev)  { 
         HttpSession session = ev.getSession();
         session.setMaxInactiveInterval(SESSION_TIMEOUT);
         session.setAttribute(Parameters.LOCALE, Parameters.DEFAULT);
         session.setAttribute(Parameters.LOCALE_MANAGER, LocaleManager.valueOf(Parameters.DEFAULT.toUpperCase()));
    }

    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    }
	
}
