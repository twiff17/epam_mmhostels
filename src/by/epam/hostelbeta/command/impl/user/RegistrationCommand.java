package by.epam.hostelbeta.command.impl.user;

import java.util.List;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.entity.Hostel;
import by.epam.hostelbeta.domain.entity.User;
import by.epam.hostelbeta.service.UserService;
import by.epam.hostelbeta.service.HostelService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;
import by.epam.hostelbeta.validator.UserValidator;

/**
 * The Class RegistrationCommand. Creates the object User and save it to the
 * database. Than creates a session and returns home page
 */
public class RegistrationCommand extends AbstractCommand {

	/** The Constant ROLE_CLIENT. */
	private static final String ROLE_CLIENT = "client";

	/** The Constant REGISTRATION_PATH. The path to the registration page */
	private static final String REGISTRATION_PATH = "path.page.registration";

	/** The Constant HOME. The name of the current page */
	private static final String HOME = "home";

	/** The Constant HOME_PATH. The path to the home page */
	private static final String HOME_PATH = "path.page.home";

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
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
		String hash = request.getParameter(Parameters.HASH);
		try {
			if (request.getSession().getAttribute(Parameters.FORM_HASH) == null
					|| !request.getSession().getAttribute(Parameters.FORM_HASH).equals(hash)) {
				if (UserValidator.validate(user)) {
					user = UserService.signUp(user);
					if (user != null) {
						HttpSession session = request.getSession();
						user.setRole(ROLE_CLIENT);
						session.setAttribute(Parameters.FORM_HASH, hash);
						session.setAttribute(Parameters.SESSION_USER, user);
						List<Hostel> hostels = HostelService.getPopularHostels();
						request.setAttribute(Parameters.HOSTEL_LIST, hostels);
						request.getSession().setAttribute(Parameters.PAGE, HOME);
						page = ConfigurationManager.getProperty(HOME_PATH);
					} else {
						request.setAttribute(Parameters.ERROR_REGISTRATION_MESSAGE,
								locManager.getResourceBundle().getString(Parameters.LOGIN_EXISTS_MESSAGE));
						page = ConfigurationManager.getProperty(REGISTRATION_PATH);
					}
				} else {
					request.setAttribute(Parameters.ERROR_REGISTRATION_MESSAGE,
							locManager.getResourceBundle().getString(Parameters.INVALID_DATA));
					page = ConfigurationManager.getProperty(REGISTRATION_PATH);
				}
			} else {
				List<Hostel> hostels = HostelService.getPopularHostels();
				request.setAttribute(Parameters.HOSTEL_LIST, hostels);
				request.getSession().setAttribute(Parameters.PAGE, HOME);
				page = ConfigurationManager.getProperty(HOME_PATH);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path in properties file", e);
		}
		return page;
	}
}
