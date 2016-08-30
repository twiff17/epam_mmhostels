package by.epam.hostelbeta.command.impl.common;

import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.entity.User;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

public class GetPageCommand extends AbstractCommand {
	private static final String SHORT_PATH = "path.page.";
	private static final String NO_ACCESS_PATH = "path.page.noaccess";
	private static final String ROLE_ADMIN = "admin";
	private static final String ADMIN_PAGE = "admin";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String pageName = request.getParameter(Parameters.PAGE);
		String page = null;
		try {
			User user = (User) request.getSession().getAttribute(Parameters.SESSION_USER);
			if (user != null && (pageName.equals(Parameters.LOGIN) || pageName.equals(Parameters.REGISTRATION))) {

			} else {
				if (user != null && !ROLE_ADMIN.equals(user.getRole()) && ADMIN_PAGE.equals(pageName)) {
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