package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.entity.Country;

// TODO: Auto-generated Javadoc
/**
 * The Interface ICountryDAO.
 */
public interface ICountryDAO {
	
	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<Country> findAll() throws DAOException;
}
