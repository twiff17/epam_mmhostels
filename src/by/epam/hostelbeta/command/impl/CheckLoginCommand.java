package by.epam.hostelbeta.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.service.LoginService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

public class CheckLoginCommand extends AbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String message = "";
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		try {
			if(LoginService.checkLogin(request.getParameter(Parameters.LOGIN))){
				message = locManager.getResourceBundle().getString(Parameters.LOGIN_NOT_AVAILABLE);
			}else{
				message = "";
			}
		} catch (ServiceException e) {
			message = Parameters.LOGIN_CHECK_ERROR;
		}
		return message;
	}

}
