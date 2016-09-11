package by.epam.hostelbeta.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionDecorator.
 */
public class ConnectionDecorator {
	
	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new connection decorator.
	 *
	 * @param connection the connection
	 */
	ConnectionDecorator(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Creates the statement.
	 *
	 * @return the statement
	 * @throws SQLException the SQL exception
	 */
	public Statement createStatement() throws SQLException {
		return connection.createStatement();
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql the sql
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql the sql
	 * @param gk the gk
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	public PreparedStatement prepareStatement(String sql, int gk) throws SQLException {
		return connection.prepareStatement(sql, gk);
	}

	/**
	 * Close.
	 */
	public void close() {
		ConnectionPool.getInstance().putback(connection);
		connection = null;
	}
}
