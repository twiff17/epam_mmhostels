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

public class CurrencyDAO implements ICurrencyDAO {
	private static final String SELECT_ALL = "SELECT `Code`, `CurrencyId` , `Name` FROM `currency`";
	private static final String INSERT_CURRENCY_RATE = "INSERT INTO `currency_rate` (`Currency`,`RateDate`, `Rate`) VALUES(?, ?, ?)";

	private static final String CODE = "Code";
	private static final String NAME = "Name";
	private static final String CURRENCY_ID = "CurrencyId";

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
			throw new DAOException("CurrencyDAO Error in findAll method", e);
		} finally {
			connection.close();
		}

		return currencyList;
	}

	public void insertCurrencyRate(CurrencyRate rate) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		try (PreparedStatement ps = connection.prepareStatement(INSERT_CURRENCY_RATE)) {
			ps.setString(1, rate.getCode());
			ps.setDate(2, java.sql.Date.valueOf(rate.getDate()));
			ps.setDouble(3, rate.getRate());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("CurrencyDAO Error inserting currency rate!", e);
		} finally {
			connection.close();
		}
	}

	private void fillCurrency(ResultSet rs, Currency currency) throws SQLException {
		currency.setCode(rs.getString(CODE));
		currency.setName(rs.getString(NAME));
		currency.setCurrencyId(rs.getInt(CURRENCY_ID));
	}
}
