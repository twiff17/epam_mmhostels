package by.epam.hostelbeta.command.impl.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.entity.Hostel;
import by.epam.hostelbeta.domain.entity.User;
import by.epam.hostelbeta.service.UserService;
import by.epam.hostelbeta.service.HostelService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

public class LoginCommand extends AbstractCommand {
	private static final String LOGIN_PATH = "path.page.login";
	private static final String HOME = "home";
	private static final String HOME_PATH = "path.page.home";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page = null;
		String login = request.getParameter(Parameters.LOGIN);
		String password = request.getParameter(Parameters.PASSWORD);
		try {
			User user = UserService.checkLoginPassword(login, password);
			if (user != null) {
				request.getSession().setAttribute(Parameters.USER_ID, user.getUserId());
				request.getSession().setAttribute(Parameters.LOGIN, user.getLogin());
				request.getSession().setAttribute(Parameters.ROLE, user.getRole());
				request.getSession().setAttribute(Parameters.BAN, user.getBan());
				request.getSession().setAttribute(Parameters.REGULAR, user.getDiscount());
				List<Hostel> hostels = HostelService.getPopularHostels();
				request.setAttribute(Parameters.HOSTEL_LIST, hostels);
				request.getSession().setAttribute(Parameters.PAGE, HOME);
				page = ConfigurationManager.getProperty(HOME_PATH);
			} else {
				LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
				request.setAttribute(Parameters.ERROR_LOGIN_PASS_MESSAGE,
						locManager.getResourceBundle().getString(Parameters.INCORRECT_LOGIN_MESSAGE));
				page = ConfigurationManager.getProperty(LOGIN_PATH);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return page;
	}
}
