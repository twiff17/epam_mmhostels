package by.epam.hostelbeta.command.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

public class GetPageCommand extends AbstractCommand{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String pageName = request.getParameter(Parameters.PAGE);
		try {
			fillRequest(request, pageName);
			request.getSession().setAttribute(Parameters.PAGE, pageName);
			return ConfigurationManager.getProperty(Parameters.SHORT_PATH + pageName);
		} catch (ServiceException e) {
			request.setAttribute("errorStackTrace", e);
			return ConfigurationManager.getProperty(Parameters.ERROR_PATH); 
		}
	}
}