package by.epam.hostelbeta.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.entity.Hostel;
import by.epam.hostelbeta.service.HostelService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

public class EditHostelCommand extends AbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		String message;

		Hostel hostel = new Hostel();
		hostel.setHostelId(Long.parseLong(request.getParameter(Parameters.HOSTEL_ID)));
		hostel.setName(request.getParameter(Parameters.NAME));
		hostel.setAddress(request.getParameter(Parameters.ADDRESS));
		hostel.setCity(request.getParameter(Parameters.CITY));
		hostel.setCountry(request.getParameter(Parameters.COUNTRY));
		hostel.setCurrency(request.getParameter(Parameters.CURRENCY));
		hostel.setDescription(request.getParameter(Parameters.DESCRIPTION));
		hostel.setPhone(request.getParameter(Parameters.PHONE));
		hostel.setStandartPrice(Integer.parseInt(request.getParameter(Parameters.STANDART_PRICE)));
		try {
			boolean result = HostelService.editHostel(hostel);
			if (result) {
				message = locManager.getResourceBundle().getString(Parameters.OPERATION_SUCCESS);
			} else {
				message = locManager.getResourceBundle().getString(Parameters.OPERATION_ERROR);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return message;
	}
}
