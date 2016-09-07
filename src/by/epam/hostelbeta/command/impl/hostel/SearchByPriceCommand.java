package by.epam.hostelbeta.command.impl.hostel;

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

public class SearchByPriceCommand extends AbstractCommand {
	private static final String HOSTELS = "hostels";
	private static final String HOSTELS_PATH = "path.page.hostels";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		String country = request.getParameter(Parameters.COUNTRY);

		request.setAttribute(Parameters.PAGE, HOSTELS);
		try {
			int minPrice = Integer.parseInt((request.getParameter(Parameters.MIN_PRICE)));
			int maxPrice = Integer.parseInt((request.getParameter(Parameters.MAX_PRICE)));

			if (maxPrice > minPrice) {
				request.setAttribute(Parameters.IS_SEARCH, true);
				List<HostelDTO> hostels = HostelService.searchByPriceAndCountry(country, minPrice, maxPrice);
				request.setAttribute(Parameters.HOSTEL_LIST, hostels);
			} else {
				List<HostelDTO> hostels = new ArrayList<HostelDTO>();
				int noOfPages = HostelService.getAllHostels(1, hostels);
				request.setAttribute(Parameters.HOSTEL_LIST, hostels);
				request.setAttribute(Parameters.NO_OF_PAGES, noOfPages);
				request.setAttribute(Parameters.CURRENT_PAGE, 1);
				request.setAttribute(Parameters.ERROR_MESSAGE,
						locManager.getResourceBundle().getString(Parameters.INVALID_PRICES));
			}
			return ConfigurationManager.getProperty(HOSTELS_PATH);
		} catch (ServiceException | NumberFormatException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path " + HOSTELS_PATH, e);
		}
	}

}
