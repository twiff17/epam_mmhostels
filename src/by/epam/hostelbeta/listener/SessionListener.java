package by.epam.hostelbeta.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    public SessionListener() {
    }

    public void sessionCreated(HttpSessionEvent ev)  { 
         HttpSession session = ev.getSession();
         session.setAttribute("locale", "en_US");
    }

    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    }
	
}
