package by.epam.hostelbeta.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.domain.entity.User;
import by.epam.hostelbeta.service.LoginService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;
import by.epam.hostelbeta.util.RequestUtil;

public class LoginCommand extends AbstractCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
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
				page = RequestUtil.createHomePage(request);
			} else {
				LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
				request.setAttribute(Parameters.ERROR_LOGIN_PASS_MESSAGE,
						locManager.getResourceBundle().getString(Parameters.INCORRECT_LOGIN_MESSAGE));
				page = ConfigurationManager.getProperty(Parameters.LOGIN_PATH);
			}
		} catch (ServiceException | DAOException e) {
			throw new CommandException(e);
		}
		return page;
	}
}
