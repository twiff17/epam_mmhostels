package by.epam.hostelbeta.listener;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import by.epam.hostelbeta.pool.ConnectionPool;
import by.epam.hostelbeta.util.CurrencyRateUpdater;

@WebListener
public class ServletListener implements ServletContextListener {
	public ServletListener() {
	}

	public void contextDestroyed(ServletContextEvent ev) {
		ConnectionPool.getInstance().closePool();
	}

	public void contextInitialized(ServletContextEvent ev) {
		Timer time = new Timer();
		CurrencyRateUpdater st = new CurrencyRateUpdater();
		time.schedule(st, 0, TimeUnit.DAYS.toMillis(1));
	}
}
