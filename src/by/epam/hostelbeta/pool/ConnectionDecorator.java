package by.epam.hostelbeta.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The Class ConnectionDecorator. The decorator for connection. Implements
 * method close for putting back connection to the pool
 */
public class ConnectionDecorator {

	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new connection decorator.
	 *
	 * @param connection
	 *            the connection
	 */
	ConnectionDecorator(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Creates a Statement object for sending SQL statements to the database.
	 * SQL statements without parameters are normally executed using Statement
	 * objects. If the same SQL statement is executed many times, it may be more
	 * efficient to use a PreparedStatement object.
	 *
	 * @return a new default Statement object
	 * @throws SQLException
	 *             if a database access error occurs or this method is called on
	 *             a closed connection
	 */
	public Statement createStatement() throws SQLException {
		return connection.createStatement();
	}

	/**
	 * Creates a Statement object for sending SQL statements to the database.
	 * SQL statements without parameters are normally executed using Statement
	 * objects. If the same SQL statement is executed many times, it may be more
	 * efficient to use a PreparedStatement object.
	 *
	 * @param sql
	 *            an SQL statement that may contain one or more '?' IN parameter
	 *            placeholders
	 * @return a new default PreparedStatement object containing the
	 *         pre-compiled SQL statement
	 * @throws SQLException
	 *             if a database access error occurs or this method is called on
	 *             a closed connection
	 */
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql
	 *            an SQL statement that may contain one or more '?' IN parameter
	 *            placeholders
	 * @param gk
	 *            a flag indicating whether auto-generated keys should be
	 *            returned; one of Statement.RETURN_GENERATED_KEYS or
	 *            Statement.NO_GENERATED_KEYS
	 * @return a new PreparedStatement object, containing the pre-compiled SQL
	 *         statement, that will have the capability of returning
	 *         auto-generated keys
	 * @throws SQLException
	 *             if a database access error occurs or this method is called on
	 *             a closed connection
	 */
	public PreparedStatement prepareStatement(String sql, int gk) throws SQLException {
		return connection.prepareStatement(sql, gk);
	}

	/**
	 * Close. Method for putting back connection to the pool
	 */
	public void close() {
		ConnectionPool.getInstance().putback(connection);
		connection = null;
	}
}
