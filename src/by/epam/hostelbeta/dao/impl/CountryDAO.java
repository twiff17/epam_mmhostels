package by.epam.hostelbeta.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.ICountryDAO;
import by.epam.hostelbeta.domain.entity.Country;
import by.epam.hostelbeta.pool.ConnectionPool;
import by.epam.hostelbeta.pool.ConnectionDecorator;

/**
 * The Class CountryDAO. Class to access the data about countries
 */
public class CountryDAO implements ICountryDAO {

	/** The Constant SELECT_ALL. */
	private static final String SELECT_ALL = "SELECT `Name` FROM `country`";

	/** The Constant NAME. */
	private static final String NAME = "Name";

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hostelbeta.dao.ICountryDAO#findAll()
	 */
	public List<Country> findAll() throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		List<Country> countries = new ArrayList<Country>();
		try (Statement st = connection.createStatement()) {
			ResultSet rs = st.executeQuery(SELECT_ALL);

			while (rs.next()) {
				Country country = new Country();
				fillCountry(rs, country);
				countries.add(country);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}

		return countries;
	}

	/**
	 * Fill country.
	 *
	 * @param rs
	 *            the ResultSet with data
	 * @param country
	 *            the country
	 * @throws SQLException
	 *             the SQL exception
	 */
	private void fillCountry(ResultSet rs, Country country) throws SQLException {
		country.setName(rs.getString(NAME));
	}
}
