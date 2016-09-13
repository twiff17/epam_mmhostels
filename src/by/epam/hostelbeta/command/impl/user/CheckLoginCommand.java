package by.epam.hostelbeta.command.impl.user;

import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.service.UserService;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

/**
 * The Class CheckLoginCommand. Checks login's availability
 */
public class CheckLoginCommand extends AbstractCommand {

	/** The Constant PROPERTY_NO_FOUND. */
	private static final String PROPERTY_NO_FOUND = "???not_found???";

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String message = null;
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		try {
			if (UserService.checkLogin(request.getParameter(Parameters.LOGIN))) {
				message = locManager.getResourceBundle().getString(Parameters.LOGIN_NOT_AVAILABLE);
			} else {
				message = "";
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			message = PROPERTY_NO_FOUND;
		}
		return message;
	}
}
