package by.epam.hostelbeta.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.entity.User;
import by.epam.hostelbeta.service.RegistrationService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

public class RegistrationCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page = null;
		User user = new User();
		user.setLogin(request.getParameter(Parameters.LOGIN));
		user.setPassword(request.getParameter(Parameters.PASSWORD));
		user.setEmail(request.getParameter(Parameters.EMAIL));
		user.setFullname(request.getParameter(Parameters.FULLNAME));
		user.setPassport(request.getParameter(Parameters.PASSPORT));
		user.setPhone(request.getParameter(Parameters.PHONE));
		try {
			if (RegistrationService.signUp(user)) {
				HttpSession session = request.getSession();
				session.setAttribute(Parameters.LOGIN, user.getLogin());
				session.setAttribute(Parameters.ROLE, Parameters.ROLE_CLIENT);
				session.setAttribute(Parameters.PAGE, Parameters.HOME);
				page = ConfigurationManager.getProperty(Parameters.HOME_PATH);
			} else {
				LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
				request.setAttribute(Parameters.ERROR_REGISTRATION_MESSAGE,
						locManager.getResourceBundle().getString(Parameters.LOGIN_EXISTS_MESSAGE));
				page = ConfigurationManager.getProperty(Parameters.REGISTRATION_PATH);
			}
		} catch (ServiceException e) {
			page = ConfigurationManager.getProperty(Parameters.ERROR_PATH);
		}
		return page;
	}
}
