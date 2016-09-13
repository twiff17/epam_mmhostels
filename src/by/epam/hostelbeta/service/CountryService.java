package by.epam.hostelbeta.service;

import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.impl.CountryDAO;
import by.epam.hostelbeta.domain.entity.Country;

/**
 * The Class CountryService.
 */
public class CountryService {

	/**
	 * Gets the all countries.
	 *
	 * @return the list of all countries
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static List<Country> getAllCountries() throws ServiceException {
		CountryDAO countryDAO = new CountryDAO();
		try {
			return countryDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
