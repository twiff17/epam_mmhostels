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
import by.epam.hostelbeta.validator.HostelValidator;

public class AddHostelCommand extends AbstractCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		String message;
		
		Hostel hostel = new Hostel();
		hostel.setName(request.getParameter(Parameters.NAME));
		hostel.setAddress(request.getParameter(Parameters.ADDRESS));
		hostel.setCity(request.getParameter(Parameters.CITY));
		hostel.setCountry(request.getParameter(Parameters.COUNTRY));
		hostel.setCurrency(request.getParameter(Parameters.CURRENCY));
		hostel.setDescription(request.getParameter(Parameters.DESCRIPTION));
		hostel.setPhone(request.getParameter(Parameters.PHONE));
		hostel.setStandartPrice(Integer.parseInt(request.getParameter(Parameters.STANDART_PRICE)));
		try {
			if(HostelValidator.validate(hostel)){
				boolean result = HostelService.addHostel(hostel);
				if (result) {
					message = locManager.getResourceBundle().getString(Parameters.OPERATION_SUCCESS);
				} else {
					message = locManager.getResourceBundle().getString(Parameters.OPERATION_ERROR);
				}
			}else{
				message = locManager.getResourceBundle().getString(Parameters.INVALID_DATA);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return message;
	}

}
