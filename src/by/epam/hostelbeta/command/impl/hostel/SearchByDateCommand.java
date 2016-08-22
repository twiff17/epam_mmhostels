package by.epam.hostelbeta.command.impl.hostel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.dto.HostelDTO;
import by.epam.hostelbeta.service.HostelService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;
import by.epam.hostelbeta.validator.OrderValidator;

public class SearchByDateCommand extends AbstractCommand {
	private static final String HOSTELS_PATH = "path.page.hostels";
	private static final String HOSTELS = "hostels";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		String country = request.getParameter(Parameters.COUNTRY);
		LocalDate inDate = LocalDate.parse(request.getParameter(Parameters.IN_DATE));
		LocalDate outDate = LocalDate.parse(request.getParameter(Parameters.OUT_DATE));
		request.setAttribute(Parameters.PAGE, HOSTELS);
		try {
			int validationResult = OrderValidator.dateValidate(inDate, outDate);
			if (validationResult == 0) {
				List<HostelDTO> hostels = HostelService.searchByDateAndCountry(country, inDate, outDate);
				request.setAttribute(Parameters.HOSTEL_LIST, hostels);
			} else {
				List<HostelDTO> hostels = new ArrayList<HostelDTO>();
				int noOfPages = HostelService.getAllHostels(1, hostels);
				request.setAttribute(Parameters.HOSTEL_LIST, hostels);
				request.setAttribute(Parameters.NO_OF_PAGES, noOfPages);
				request.setAttribute(Parameters.CURRENT_PAGE, 1);
				if (validationResult == 1) {
					request.setAttribute(Parameters.ERROR_MESSAGE,
							locManager.getResourceBundle().getString(Parameters.IN_DATE_AFTER_OUT));
				} else {
					request.setAttribute(Parameters.ERROR_MESSAGE,
							locManager.getResourceBundle().getString(Parameters.DATE_BEFORE_TODAY));
				}
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return ConfigurationManager.getProperty(HOSTELS_PATH);
	}

}
