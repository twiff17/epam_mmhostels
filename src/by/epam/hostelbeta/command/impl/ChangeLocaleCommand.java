package by.epam.hostelbeta.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;
import by.epam.hostelbeta.util.RequestUtil;

public class ChangeLocaleCommand extends AbstractCommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String locale = request.getParameter(Parameters.LOCALE);
		LocaleManager localeManager = LocaleManager.valueOf(locale.toUpperCase());
		HttpSession session = request.getSession();
		session.setAttribute(Parameters.LOCALE, locale);
		session.setAttribute(Parameters.LOCALE_MANAGER, localeManager);
		try {
			return RequestUtil.createHomePage(request);
		} catch (DAOException e) {
			throw new CommandException(e);
		}
	}
}
