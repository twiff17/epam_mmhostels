package by.epam.hostelbeta.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

public class ChangeLocaleCommand implements ICommand {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String locale = request.getParameter(Parameters.LOCALE);
		LocaleManager localeManager = LocaleManager.valueOf(locale.toUpperCase());
		HttpSession session = request.getSession();
		session.setAttribute(Parameters.LOCALE, locale);
		session.setAttribute(Parameters.LOCALE_MANAGER, localeManager);
		String page = (String) session.getAttribute(Parameters.PAGE);
		if (page != null) {
			return ConfigurationManager.getProperty(Parameters.SHORT_PATH + page);
		}else{
			return ConfigurationManager.getProperty(Parameters.HOME_PATH);
		}
	}
}
