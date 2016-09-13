package by.epam.hostelbeta.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Interface ICommand. All commands implement this interface
 */
public interface ICommand {

	/**
	 * Execute. 
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the path of the page or a message
	 * @throws CommandException
	 *             the command exception, throws when some exceptions is thrown
	 *             in this method
	 */
	String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;

	/**
	 * Fill country list. For 
	 *
	 * @param request
	 *            the request
	 * @throws CommandException
	 *             the command exception
	 */
	void fillCountryList(HttpServletRequest request) throws CommandException;
}
