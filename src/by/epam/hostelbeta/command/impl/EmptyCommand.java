package by.epam.hostelbeta.command.impl;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

public class EmptyCommand extends AbstractCommand implements ICommand {
	@Override
	
	public String execute(HttpServletRequest request, HttpServletResponse response){
		try{
			fillRequest(request, Parameters.HOME);
			request.getSession().setAttribute(Parameters.PAGE, Parameters.HOME);
			return ConfigurationManager.getProperty(Parameters.HOME_PATH);
		}catch(ServiceException e){
			request.setAttribute("errorStackTrace", e);
			return ConfigurationManager.getProperty(Parameters.ERROR_PATH); 
		}
	}
}
