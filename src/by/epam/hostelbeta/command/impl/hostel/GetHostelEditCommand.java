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
import by.epam.hostelbeta.util.Parameters;

// TODO: Auto-generated Javadoc
/**
 * The Class GetHostelEditCommand.
 */
public class GetHostelEditCommand extends AbstractCommand {
	
	/** The Constant HOSTEL_ADD_PATH. */
	private static final String HOSTEL_ADD_PATH = "path.page.hostel-add";
	
	/** The Constant ADMIN. */
	private static final String ADMIN = "admin";

	/* (non-Javadoc)
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
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
			return ConfigurationManager.getProperty(HOSTEL_ADD_PATH);
		} catch (ServiceException | NumberFormatException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path " + HOSTEL_ADD_PATH, e);
		}
	}

}
