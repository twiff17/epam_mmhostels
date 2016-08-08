package by.epam.hostelbeta.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import by.epam.hostelbeta.pool.ConnectionPool;

@WebListener
public class ServletListener implements ServletContextListener {
    public ServletListener() {
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         ConnectionPool.getInstance().close();
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    }
	
}
