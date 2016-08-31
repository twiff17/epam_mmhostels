package by.epam.hostelbeta.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDecorator {
	private Connection connection;

	ConnectionDecorator(Connection connection) {
		this.connection = connection;
	}

	public Statement createStatement() throws SQLException {
		return connection.createStatement();
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	public PreparedStatement prepareStatement(String sql, int gk) throws SQLException {
		return connection.prepareStatement(sql, gk);
	}

	public void close() {
		ConnectionPool.getInstance().putback(connection);
	}
}
