package by.epam.hostelbeta.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.domain.entity.User;
import by.epam.hostelbeta.service.RegistrationService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;
import by.epam.hostelbeta.util.RequestUtil;
import by.epam.hostelbeta.validator.UserValidator;

public class RegistrationCommand extends AbstractCommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page = null;
		User user = new User();
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		user.setLogin(request.getParameter(Parameters.LOGIN));
		user.setPassword(request.getParameter(Parameters.PASSWORD));
		user.setEmail(request.getParameter(Parameters.EMAIL));
		user.setFullname(request.getParameter(Parameters.FULLNAME));
		user.setPassport(request.getParameter(Parameters.PASSPORT));
		user.setPhone(request.getParameter(Parameters.PHONE));
		try {
			if (UserValidator.validate(user)) {
				user = RegistrationService.signUp(user);
				if (user != null) {
					HttpSession session = request.getSession();
					session.setAttribute(Parameters.LOGIN, user.getLogin());
					request.getSession().setAttribute(Parameters.USER_ID, user.getUserId());
					session.setAttribute(Parameters.ROLE, Parameters.ROLE_CLIENT);
					page = RequestUtil.createHomePage(request);
				} else {
					request.setAttribute(Parameters.ERROR_REGISTRATION_MESSAGE,
							locManager.getResourceBundle().getString(Parameters.LOGIN_EXISTS_MESSAGE));
					page = ConfigurationManager.getProperty(Parameters.REGISTRATION_PATH);
				}
			} else {
				request.setAttribute(Parameters.ERROR_REGISTRATION_MESSAGE,
						locManager.getResourceBundle().getString(Parameters.INVALID_DATA));
				page = ConfigurationManager.getProperty(Parameters.REGISTRATION_PATH);
			}
		} catch (ServiceException | DAOException e) {
			throw new CommandException(e);
		}
		return page;
	}
}
