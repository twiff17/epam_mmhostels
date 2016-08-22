package by.epam.hostelbeta.command.impl.common;

import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

public class GetPageCommand extends AbstractCommand {
	private static final String SHORT_PATH = "path.page.";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String pageName = request.getParameter(Parameters.PAGE);
		String page = null;
		try {
			if (request.getSession().getAttribute(Parameters.ROLE) != null
					&& (pageName.equals(Parameters.LOGIN) || pageName.equals(Parameters.REGISTRATION))) {

			} else {
				page = ConfigurationManager.getProperty(SHORT_PATH + pageName);
				request.getSession().setAttribute(Parameters.PAGE, pageName);
			}
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page " + pageName, e);
		}
		return page;
	}
}