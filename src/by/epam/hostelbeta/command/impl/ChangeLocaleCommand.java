package by.epam.hostelbeta.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.entity.Hostel;
import by.epam.hostelbeta.service.HostelService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

public class ChangeLocaleCommand extends AbstractCommand {
	private static final String HOME = "home";
	private static final String HOME_PATH = "path.page.home";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String locale = request.getParameter(Parameters.LOCALE);
		LocaleManager localeManager = LocaleManager.valueOf(locale.toUpperCase());
		HttpSession session = request.getSession();
		session.setAttribute(Parameters.LOCALE, locale);
		session.setAttribute(Parameters.LOCALE_MANAGER, localeManager);
		try {
			List<Hostel> hostels = HostelService.getPopularHostels();
			request.setAttribute(Parameters.HOSTEL_LIST, hostels);
			session.setAttribute(Parameters.PAGE, HOME);
			return ConfigurationManager.getProperty(HOME_PATH);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
	}
}
