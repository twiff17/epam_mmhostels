package by.epam.hostelbeta.command.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.service.PageService;
import by.epam.hostelbeta.service.ServiceException;
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
		String pathPage = null;
		try {
			if (page != null) {
				HashMap<String, Object> attributes = PageService.getPageAttrubutes(page, request.getParameterMap());
				if (attributes != null) {
					for (Map.Entry<String, Object> entry : attributes.entrySet()) {
						request.setAttribute(entry.getKey(), entry.getValue());
					}
				}
				pathPage = ConfigurationManager.getProperty(Parameters.SHORT_PATH + page);
			} else {
				pathPage = ConfigurationManager.getProperty(Parameters.HOME_PATH);
			}
		} catch (ServiceException e) {
			pathPage = ConfigurationManager.getProperty(Parameters.ERROR_PATH);
		}
		return pathPage;
	}
}
