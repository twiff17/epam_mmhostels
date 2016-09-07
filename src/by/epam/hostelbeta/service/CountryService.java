package by.epam.hostelbeta.service;

import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.impl.CountryDAO;
import by.epam.hostelbeta.domain.entity.Country;

public class CountryService {
	public static List<Country> getAllCountries() throws ServiceException {
		CountryDAO countryDAO = new CountryDAO();
		try {
			return countryDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException("CountryService Error getting all countries!", e);
		}
	}
}
