package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.entity.Currency;
import by.epam.hostelbeta.domain.entity.CurrencyRate;

/**
 * The Interface ICurrencyDAO.
 */
public interface ICurrencyDAO {

	/**
	 * Finds all currencies.
	 *
	 * @return the list of all currencies
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	List<Currency> findAll() throws DAOException;

	/**
	 * Inserts currency rate.
	 *
	 * @param rate
	 *            - the rate
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	void insertCurrencyRate(CurrencyRate rate) throws DAOException;
}
