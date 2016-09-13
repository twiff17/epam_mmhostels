package by.epam.hostelbeta.command.impl.user;

import java.util.List;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.entity.User;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.service.UserService;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

/**
 * The Class GetUsersCommand. Fills users list and returns the page with this
 * list
 */
public class GetUsersCommand extends AbstractCommand {

	/** The Constant USER_PATH. */
	private static final String USER_PATH = "path.page.user";

	/** The Constant ADMIN. */
	private static final String ADMIN = "admin";

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			List<User> users = UserService.getAllUsers();
			request.setAttribute(Parameters.PAGE, ADMIN);
			request.setAttribute(Parameters.USER_LIST, users);
			return ConfigurationManager.getProperty(USER_PATH);
		} catch (ServiceException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path " + USER_PATH, e);
		}
	}

}
