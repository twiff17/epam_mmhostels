package by.epam.hostelbeta.command.impl.user;

import java.util.List;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

/**
 * The Class LoginCommand. Checks user's login and password. Then creates the
 * session and returns home page if the user exists, else returns login page
 * with error message
 */
public class LoginCommand extends AbstractCommand {

	/** The Constant LOGIN_PATH. The path to the login page */
	private static final String LOGIN_PATH = "path.page.login";

	/** The Constant HOME. */
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
		String login = request.getParameter(Parameters.LOGIN);
		String password = request.getParameter(Parameters.PASSWORD);
		try {
			User user = UserService.checkLoginPassword(login, password);
			if (user != null) {
				request.getSession().setAttribute(Parameters.SESSION_USER, user);
				List<Hostel> hostels = HostelService.getPopularHostels();
				request.setAttribute(Parameters.HOSTEL_LIST, hostels);
				request.getSession().setAttribute(Parameters.PAGE, HOME);
				page = ConfigurationManager.getProperty(HOME_PATH);
			} else {
				LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
				request.setAttribute(Parameters.ERROR_LOGIN_PASS_MESSAGE,
						locManager.getResourceBundle().getString(Parameters.INCORRECT_LOGIN_MESSAGE));
				page = ConfigurationManager.getProperty(LOGIN_PATH);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path in properties file", e);
		}
		return page;
	}
}
