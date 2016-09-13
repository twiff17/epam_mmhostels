package by.epam.hostelbeta.command.impl.common;

import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.entity.User;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

/**
 * The Class GetPageCommand. Returns the page which is specified in request
 * parameter "page".
 */
public class GetPageCommand extends AbstractCommand {

	/** The Constant SHORT_PATH. The prefix to the all pages */
	private static final String SHORT_PATH = "path.page.";

	/** The Constant NO_ACCESS_PATH. The path to the no_access page */
	private static final String NO_ACCESS_PATH = "path.page.noaccess";

	/** The Constant ROLE_ADMIN. */
	private static final String ROLE_ADMIN = "admin";

	/** The Constant ADMIN_PAGE. The name of admin page */
	private static final String ADMIN_PAGE = "admin";

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String pageName = request.getParameter(Parameters.PAGE);
		String page = null;
		try {
			User user = (User) request.getSession().getAttribute(Parameters.SESSION_USER);
			if (user != null && (pageName.equals(Parameters.LOGIN) || pageName.equals(Parameters.REGISTRATION))) {

			} else {
				if ((user == null && ADMIN_PAGE.equals(pageName))
						|| (user != null && !ROLE_ADMIN.equals(user.getRole()) && ADMIN_PAGE.equals(pageName))) {
					page = ConfigurationManager.getProperty(NO_ACCESS_PATH);
				} else {
					page = ConfigurationManager.getProperty(SHORT_PATH + pageName);
					request.getSession().setAttribute(Parameters.PAGE, pageName);
				}
			}
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page " + pageName, e);
		}
		return page;
	}
}