package by.epam.hostelbeta.command.impl.user;

import java.util.List;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.entity.Hostel;
import by.epam.hostelbeta.service.HostelService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

/**
 * The Class LogoutCommand. Invalidates the session and returns home page
 */
public class LogoutCommand extends AbstractCommand {

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
		String locale = (String) request.getSession().getAttribute(Parameters.LOCALE);
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		request.getSession().invalidate();
		request.getSession().setAttribute(Parameters.LOCALE, locale);
		request.getSession().setAttribute(Parameters.LOCALE_MANAGER, locManager);
		try {
			List<Hostel> hostels = HostelService.getPopularHostels();
			request.setAttribute(Parameters.HOSTEL_LIST, hostels);
			request.getSession().setAttribute(Parameters.PAGE, HOME);
			return ConfigurationManager.getProperty(HOME_PATH);
		} catch (ServiceException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path " + HOME_PATH, e);
		}
	}

}
