package by.epam.hostelbeta.command.impl.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.service.UserService;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

public class BanUserCommand extends AbstractCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		String message;
		try {
			long userId = Long.parseLong(request.getParameter(Parameters.USER_ID));
			UserService.banUser(userId);
			message = locManager.getResourceBundle().getString(Parameters.OPERATION_SUCCESS);
		} catch (ServiceException | NumberFormatException e) {
			throw new CommandException(e);
		}
		return message;
	}

}
