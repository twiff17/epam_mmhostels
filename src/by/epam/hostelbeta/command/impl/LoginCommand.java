package by.epam.hostelbeta.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.service.LoginService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;

public class LoginCommand implements ICommand {
	private static final String PARAM_LOGIN = "login"; // для повышения читабельности
	private static final String PARAM_PASSWORD = "password";
	private static final String HOME_PAGE = "path.page.home";
	private static final String LOGIN_PAGE = "path.page.login";
	private static final String ERROR_PAGE = "path.page.error";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page = null;
		String login = request.getParameter(PARAM_LOGIN);
		String password = request.getParameter(PARAM_PASSWORD);
		try {
			if (LoginService.checkLogin(login, password)) {
				page = ConfigurationManager.getProperty(HOME_PAGE);
			} else {
				request.setAttribute("errorLoginPassMessage", "Incorrect login and password");
				page = ConfigurationManager.getProperty(LOGIN_PAGE);
			}
		} catch (ServiceException e) {
			page = ConfigurationManager.getProperty(ERROR_PAGE);
		}
		return page;
	}
}
