package by.epam.hostelbeta.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.ICurrencyDAO;
import by.epam.hostelbeta.domain.entity.Currency;
import by.epam.hostelbeta.pool.ConnectionPool;
import by.epam.hostelbeta.pool.ConnectionWrapper;

public class CurrencyDAO implements ICurrencyDAO {
	private static final String SELECT_ALL = "SELECT `Code`, `Name` FROM `currency`";

	private static final String CODE = "Code";
	private static final String NAME = "Name";

	public List<Currency> findAll() throws DAOException {
		ConnectionWrapper connection = ConnectionPool.getInstance().retrieve();
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

	private void fillCurrency(ResultSet rs, Currency currency) throws SQLException {
		currency.setCode(rs.getString(CODE));
		currency.setName(rs.getString(NAME));
	}
}