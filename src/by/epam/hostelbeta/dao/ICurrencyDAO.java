package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.entity.Currency;
import by.epam.hostelbeta.domain.entity.CurrencyRate;

// TODO: Auto-generated Javadoc
/**
 * The Interface ICurrencyDAO.
 */
public interface ICurrencyDAO {
	
	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<Currency> findAll() throws DAOException;

	/**
	 * Insert currency rate.
	 *
	 * @param rate the rate
	 * @throws DAOException the DAO exception
	 */
	void insertCurrencyRate(CurrencyRate rate) throws DAOException;
}
