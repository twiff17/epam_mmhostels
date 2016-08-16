package by.epam.hostelbeta.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.service.OrderService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

public class RejectOrderCommand extends AbstractCommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		String message;
		try {
			long orderId = Long.parseLong(request.getParameter(Parameters.ORDER_ID));
			boolean result = OrderService.rejectOrder(orderId);
			if (result) {
				message = locManager.getResourceBundle().getString(Parameters.OPERATION_SUCCESS);
			} else {
				message = locManager.getResourceBundle().getString(Parameters.OPERATION_ERROR);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		} catch (NumberFormatException e) {
			throw new CommandException("Invalid orderId value", e);
		}
		return message;
	}
}
