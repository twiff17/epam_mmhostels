package by.epam.hostelbeta.command.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.service.PageService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

public class GetPageCommand extends AbstractCommand implements ICommand{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String pageName = request.getParameter(Parameters.PAGE);
		try {
			HashMap<String, Object> attributes = PageService.getPageAttrubutes(pageName, request.getParameterMap());
			if(attributes != null){
				for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			        request.setAttribute(entry.getKey(), entry.getValue());          
			    }
			}
			request.getSession().setAttribute(Parameters.PAGE, pageName);
			return ConfigurationManager.getProperty(Parameters.SHORT_PATH + pageName);
		} catch (ServiceException e) {
			request.setAttribute("errorStackTrace", e);
			return ConfigurationManager.getProperty(Parameters.ERROR_PATH); 
		}
	}
}