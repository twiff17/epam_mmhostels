package by.epam.hostelbeta.listener;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import by.epam.hostelbeta.pool.ConnectionPool;
import by.epam.hostelbeta.util.CurrencyRateUpdater;

/**
 * The listener interface for receiving servlet events. The class that is
 * interested in processing a servlet event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addServletListener<code> method. When the servlet event
 * occurs, that object's appropriate method is invoked.
 *
 * @see ServletEvent
 */
@WebListener
public class ServletListener implements ServletContextListener {

	/**
	 * Instantiates a new servlet listener.
	 */
	public ServletListener() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent ev) {
		ConnectionPool.getInstance().closePool();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet.
	 * ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent ev) {
		Timer time = new Timer();
		CurrencyRateUpdater st = new CurrencyRateUpdater();
		time.schedule(st, 0, TimeUnit.DAYS.toMillis(1));
	}
}
