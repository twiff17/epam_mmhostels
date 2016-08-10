package by.epam.hostelbeta.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.domain.entity.User;
import by.epam.hostelbeta.service.RegistrationService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

public class RegistrationCommand extends AbstractCommand {
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
			user = RegistrationService.signUp(user);
			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute(Parameters.LOGIN, user.getLogin());
				request.getSession().setAttribute(Parameters.USER_ID, user.getUserId());
				session.setAttribute(Parameters.ROLE, Parameters.ROLE_CLIENT);
				session.setAttribute(Parameters.PAGE, Parameters.HOME);
				fillRequest(request, Parameters.HOME);
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
