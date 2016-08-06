package by.epam.hostelbeta.dao;

import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.hostelbeta.pool.ConnectionPool;
import by.epam.hostelbeta.pool.ConnectionWrapper;

public abstract class AbstractDAO {
	static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

	protected ConnectionWrapper connection;

	public AbstractDAO() {
		connection = ConnectionPool.getInstance().retrieve();
	}

	public void closeConnection() {
		connection.close();
	}

	protected void closeStatement(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			LOGGER.error("Error closing statement!");
		}
	}
}
