package by.epam.hostelbeta.dao.impl;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.ICurrencyDAO;
import by.epam.hostelbeta.domain.entity.Currency;
import by.epam.hostelbeta.domain.entity.CurrencyRate;
import by.epam.hostelbeta.pool.ConnectionPool;
import by.epam.hostelbeta.pool.ConnectionDecorator;

/**
 * The Class CurrencyDAO. Class to access the data about currencies
 */
public class CurrencyDAO implements ICurrencyDAO {

	/** The Constant SELECT_ALL. */
	private static final String SELECT_ALL = "SELECT `Code`, `CurrencyId` , `Name` FROM `currency`";

	/** The Constant INSERT_CURRENCY_RATE. */
	private static final String INSERT_CURRENCY_RATE = "INSERT INTO `currency_rate` (`Currency`,`RateDate`, `Rate`) VALUES(?, ?, ?)";

	/** The Constant CODE. */
	private static final String CODE = "Code";

	/** The Constant NAME. */
	private static final String NAME = "Name";

	/** The Constant CURRENCY_ID. */
	private static final String CURRENCY_ID = "CurrencyId";

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hostelbeta.dao.ICurrencyDAO#findAll()
	 */
	public List<Currency> findAll() throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		List<Currency> currencyList = new ArrayList<Currency>();
		try (Statement st = connection.createStatement()) {
			ResultSet rs = st.executeQuery(SELECT_ALL);

			while (rs.next()) {
				Currency currency = new Currency();
				fillCurrency(rs, currency);
				currencyList.add(currency);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}

		return currencyList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * by.epam.hostelbeta.dao.ICurrencyDAO#insertCurrencyRate(by.epam.hostelbeta
	 * .domain.entity.CurrencyRate)
	 */
	public void insertCurrencyRate(CurrencyRate rate) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		try (PreparedStatement ps = connection.prepareStatement(INSERT_CURRENCY_RATE)) {
			ps.setString(1, rate.getCode());
			ps.setDate(2, java.sql.Date.valueOf(rate.getDate()));
			ps.setDouble(3, rate.getRate());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
	}

	/**
	 * Fill currency.
	 *
	 * @param rs
	 *            the ResultSet with data
	 * @param currency
	 *            the currency
	 * @throws SQLException
	 *             the SQL exception
	 */
	private void fillCurrency(ResultSet rs, Currency currency) throws SQLException {
		currency.setCode(rs.getString(CODE));
		currency.setName(rs.getString(NAME));
		currency.setCurrencyId(rs.getInt(CURRENCY_ID));
	}
}
