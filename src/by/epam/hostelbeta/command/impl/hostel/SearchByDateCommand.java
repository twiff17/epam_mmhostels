package by.epam.hostelbeta.command.impl.hostel;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

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

/**
 * The Class SearchByDateCommand. Searches available hostels for given date
 */
public class SearchByDateCommand extends AbstractCommand {

	/** The Constant HOSTELS_PATH. The path to the hostels page */
	private static final String HOSTELS_PATH = "path.page.hostels";

	/** The Constant HOSTELS. The name of current page */
	private static final String HOSTELS = "hostels";

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		String country = request.getParameter(Parameters.COUNTRY);

		request.setAttribute(Parameters.PAGE, HOSTELS);
		try {
			LocalDate inDate = LocalDate.parse(request.getParameter(Parameters.IN_DATE));
			LocalDate outDate = LocalDate.parse(request.getParameter(Parameters.OUT_DATE));

			int validationResult = OrderValidator.dateValidate(inDate, outDate);
			if (validationResult == 0) {
				request.setAttribute(Parameters.IS_SEARCH, true);
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
			return ConfigurationManager.getProperty(HOSTELS_PATH);
		} catch (ServiceException | DateTimeParseException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path " + HOSTELS_PATH, e);
		}
	}

}
