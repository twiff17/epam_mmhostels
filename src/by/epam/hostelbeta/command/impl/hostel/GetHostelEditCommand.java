package by.epam.hostelbeta.command.impl.hostel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.entity.Country;
import by.epam.hostelbeta.domain.entity.Currency;
import by.epam.hostelbeta.domain.entity.Hostel;
import by.epam.hostelbeta.service.CountryService;
import by.epam.hostelbeta.service.CurrencyService;
import by.epam.hostelbeta.service.HostelService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

public class GetHostelEditCommand extends AbstractCommand {
	private static final String HOSTEL_ADD_PAGE = "path.page.hostel-add";
	private static final String ADMIN = "admin";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			Hostel hostel = HostelService.getHostelById(Long.parseLong(request.getParameter(Parameters.HOSTEL_ID)));
			List<Country> countries = CountryService.getAllCountries();
			List<Currency> currencyList = CurrencyService.getAllCurrency();
			request.setAttribute(Parameters.PAGE, ADMIN);
			request.setAttribute(Parameters.COUNTRY_LIST, countries);
			request.setAttribute(Parameters.CURRENCY_LIST, currencyList);
			request.setAttribute(Parameters.HOSTEL, hostel);
		} catch (ServiceException | NumberFormatException e) {
			throw new CommandException(e);
		}
		return ConfigurationManager.getProperty(HOSTEL_ADD_PAGE);
	}

}
