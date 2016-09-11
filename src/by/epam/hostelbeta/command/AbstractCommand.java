package by.epam.hostelbeta.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.hostelbeta.domain.entity.Country;
import by.epam.hostelbeta.service.CountryService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.Parameters;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractCommand.
 */
public abstract class AbstractCommand implements ICommand {
	
	/* (non-Javadoc)
	 * @see by.epam.hostelbeta.command.ICommand#fillCountryList(javax.servlet.http.HttpServletRequest)
	 */
	public void fillCountryList(HttpServletRequest request) throws CommandException {
		try {
			List<Country> countries = CountryService.getAllCountries();
			request.setAttribute(Parameters.COUNTRY_LIST, countries);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
	}
}
