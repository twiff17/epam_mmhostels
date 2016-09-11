package by.epam.hostelbeta.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface ICommand.
 */
public interface ICommand {
	
	/**
	 * Execute.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the string
	 * @throws CommandException the command exception
	 */
	String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;

	/**
	 * Fill country list.
	 *
	 * @param request the request
	 * @throws CommandException the command exception
	 */
	void fillCountryList(HttpServletRequest request) throws CommandException;
}
