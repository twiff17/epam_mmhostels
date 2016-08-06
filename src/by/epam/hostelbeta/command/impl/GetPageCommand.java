package by.epam.hostelbeta.command.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.service.PageService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;

public class GetPageCommand implements ICommand{
	private static final String ERROR_PAGE = "path.page.error";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String pageName = request.getParameter("page");
		try {
			HashMap<String, Object> attributes = PageService.getPageAttrubutes(pageName);
			if(attributes != null){
				for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			        request.setAttribute(entry.getKey(), entry.getValue());          
			    }
			}
			request.getSession().setAttribute("page", pageName);
			return ConfigurationManager.getProperty("path.page." + request.getParameter("page"));
		} catch (ServiceException e) {
			request.setAttribute("errorStackTrace", e);
			return ConfigurationManager.getProperty(ERROR_PAGE); 
		}
	}
}
