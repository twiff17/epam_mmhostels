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
 * The Class AddDiscountCommand. Adds discount to the given user
 */
public class AddDiscountCommand extends AbstractCommand {

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
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		String message;
		try {
			long userId = Long.parseLong(request.getParameter(Parameters.USER_ID));
			UserService.addDiscount(userId);
			message = locManager.getResourceBundle().getString(Parameters.OPERATION_SUCCESS);
		} catch (ServiceException | NumberFormatException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			message = PROPERTY_NO_FOUND;
		}
		return message;
	}

}
