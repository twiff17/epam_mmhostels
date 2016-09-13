package by.epam.hostelbeta.command.impl.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

/**
 * The Class ChangeLocaleCommand. Changes the application's interface language
 */
public class ChangeLocaleCommand extends AbstractCommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String locale = request.getParameter(Parameters.LOCALE);
		LocaleManager localeManager = LocaleManager.valueOf(locale.toUpperCase());
		HttpSession session = request.getSession();
		session.setAttribute(Parameters.LOCALE, locale);
		session.setAttribute(Parameters.LOCALE_MANAGER, localeManager);
		return null;
	}
}
