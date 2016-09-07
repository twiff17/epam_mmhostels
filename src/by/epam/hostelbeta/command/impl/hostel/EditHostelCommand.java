package by.epam.hostelbeta.command.impl.hostel;

import java.util.List;
import java.util.MissingResourceException;

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
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;
import by.epam.hostelbeta.validator.HostelValidator;

public class EditHostelCommand extends AbstractCommand {
	private static final String HOSTEL_ADD_PATH = "path.page.hostel-add";
	private static final String HOSTEL_PATH = "path.page.hostel";
	private static final String ADMIN = "admin";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page = null;

		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		Hostel hostel = new Hostel();
		request.setAttribute(Parameters.PAGE, ADMIN);
		try {
			hostel.setHostelId(Long.parseLong(request.getParameter(Parameters.HOSTEL_ID)));
			hostel.setName(request.getParameter(Parameters.NAME));
			hostel.setAddress(request.getParameter(Parameters.ADDRESS));
			hostel.setCity(request.getParameter(Parameters.CITY));
			hostel.setCountry(request.getParameter(Parameters.COUNTRY));
			hostel.setCurrency(request.getParameter(Parameters.CURRENCY));
			hostel.setDescription(request.getParameter(Parameters.DESCRIPTION));
			hostel.setPhone(request.getParameter(Parameters.PHONE));
			hostel.setStandartPrice(Integer.parseInt(request.getParameter(Parameters.STANDART_PRICE)));

			if (HostelValidator.editValidate(hostel)) {
				HostelService.editHostel(hostel);
				List<Hostel> hostels = HostelService.getAllHostels();
				request.setAttribute(Parameters.HOSTEL_LIST, hostels);
				page = ConfigurationManager.getProperty(HOSTEL_PATH);
			} else {
				List<Country> countries = CountryService.getAllCountries();
				List<Currency> currencyList = CurrencyService.getAllCurrency();
				request.setAttribute(Parameters.COUNTRY_LIST, countries);
				request.setAttribute(Parameters.CURRENCY_LIST, currencyList);
				request.setAttribute(Parameters.ERROR_ADD_HOSTEL_MESSAGE,
						locManager.getResourceBundle().getString(Parameters.INVALID_DATA));
				request.setAttribute(Parameters.HOSTEL, hostel);
				page = ConfigurationManager.getProperty(HOSTEL_ADD_PATH);
			}
		} catch (ServiceException | NumberFormatException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path in properties file", e);
		}
		return page;
	}
}
