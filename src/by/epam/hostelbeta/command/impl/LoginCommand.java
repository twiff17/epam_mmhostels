package by.epam.hostelbeta.command.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.domain.entity.User;
import by.epam.hostelbeta.service.LoginService;
import by.epam.hostelbeta.service.PageService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

public class LoginCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page = null;
		String login = request.getParameter(Parameters.LOGIN);
		String password = request.getParameter(Parameters.PASSWORD);
		try {
			User user = LoginService.checkLoginPassword(login, password);
			if (user != null) {
				request.getSession().setAttribute(Parameters.USER_ID, user.getUserId());
				request.getSession().setAttribute(Parameters.LOGIN, user.getLogin());
				request.getSession().setAttribute(Parameters.ROLE, user.getRole());
				request.getSession().setAttribute(Parameters.PAGE, Parameters.HOME);
				HashMap<String, Object> attributes = PageService.getPageAttrubutes(Parameters.HOME, request.getParameterMap());
				if(attributes != null){
					for (Map.Entry<String, Object> entry : attributes.entrySet()) {
				        request.setAttribute(entry.getKey(), entry.getValue());          
				    }
				}
				page = ConfigurationManager.getProperty(Parameters.HOME_PATH);
			} else {
				LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
				request.setAttribute(Parameters.ERROR_LOGIN_PASS_MESSAGE,
						locManager.getResourceBundle().getString(Parameters.INCORRECT_LOGIN_MESSAGE));
				page = ConfigurationManager.getProperty(Parameters.LOGIN_PATH);
			}
		} catch (ServiceException e) {
			request.setAttribute("errorStackTrace", e);
			page = ConfigurationManager.getProperty(Parameters.ERROR_PATH);
		}
		return page;
	}
}
