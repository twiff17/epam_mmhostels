package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.entity.Country;

/**
 * The Interface ICountryDAO.
 */
public interface ICountryDAO {

	/**
	 * Finds all countries.
	 *
	 * @return the list of all countries
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	List<Country> findAll() throws DAOException;
}
